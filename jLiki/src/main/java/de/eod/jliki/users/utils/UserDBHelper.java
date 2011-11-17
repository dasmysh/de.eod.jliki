/**
 * File: UserDBHelper.java
 * GIT: $Id$
 *
 * Copyright (C) 2011 by The jLiki Programming Team.
 *
 * This file is part of jLiki.
 *
 * jLiki is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jLiki is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jLiki.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Author: Sebastian Maisch
 * Last changes:
 * 13.11.2011: File creation.
 */
package de.eod.jliki.users.utils;

import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import de.eod.jliki.db.servlets.DBSetup;
import de.eod.jliki.users.dbbeans.Permission;
import de.eod.jliki.users.dbbeans.Permission.PermissionType;
import de.eod.jliki.users.dbbeans.User;
import de.eod.jliki.users.dbbeans.User.ActiveState;
import de.eod.jliki.users.dbbeans.UserGroup;
import de.eod.jliki.users.jsfbeans.LoginBean;
import de.eod.jliki.util.PasswordHashUtility;

/**
 * Helper class for all user related database activitys.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public final class UserDBHelper {

    /** holds the logger. */
    // private static final Logger LOGGER = Logger.getLogger(UserDBHelper.class);

    /**
     * Prevents class construction.<br/>
     */
    private UserDBHelper() {
    }

    /**
     * Adds the user "admin" to the database if it does not exist.<br/>
     * @param user the user to add
     * @return a hash string of the added user if the user did not exist, <code>null</code> if it existed or insertion failed
     */
    public static String addUserToDB(final User user) {
        final SessionFactory sf = DBSetup.getDbManager().getSessionFactory();
        final Session session = sf.openSession();
        final Transaction trx = session.beginTransaction();
        Query query = session.createQuery("from User where username=:username or email=:email");
        query.setString("username", user.getUsername());
        query.setString("email", user.getEmail());

        User dbUser = null;
        String userhash = null;
        if (!query.iterate().hasNext()) {
            session.save(user);
            query = session.createQuery("from User where username=:username");
            query.setString("username", user.getUsername());
            final Iterator<?> it = query.iterate();
            if (query.iterate().hasNext()) {
                dbUser = (User) it.next();
                userhash = PasswordHashUtility.generateHashForUrl(dbUser.toString());
            }
        }

        trx.commit();
        session.close();

        return userhash;
    }

    /**
     * Tests if a user is in the database.<br/>
     * @param username the username to check
     * @return <code>true</code> if the user is in the database
     */
    public static boolean isUserInDB(final String username) {
        final SessionFactory sf = DBSetup.getDbManager().getSessionFactory();
        final Session session = sf.openSession();
        final Transaction trx = session.beginTransaction();
        final Query query = session.createQuery("from User where username=:username");
        query.setString("username", username);

        final boolean isUserInDB = query.iterate().hasNext();

        trx.commit();
        session.close();

        return isUserInDB;
    }

    /**
     * Activates a user with the given hash and logs it in.<br/>
     * @param username the username to activate
     * @param userHash the users hash
     * @param userLogin the login object to log the user in
     * @return <code>true</code> if activation worked, <code>false</code> if not
     */
    public static boolean activateUser(final String username, final String userHash, final LoginBean userLogin) {
        final SessionFactory sf = DBSetup.getDbManager().getSessionFactory();
        final Session session = sf.openSession();
        final Transaction trx = session.beginTransaction();
        final Query userQuery = session.createQuery("select user from User as user where user.username = :username");
        userQuery.setString("username", username);

        User dbUser = null;
        boolean didActivate = false;
        final Iterator<?> it = userQuery.iterate();
        if (it.hasNext()) {
            dbUser = (User) it.next();
            final String dbUserHash = PasswordHashUtility.generateHashForUrl(dbUser.toString());
            if (userHash.equals(dbUserHash) && dbUser.getActive() == ActiveState.INACTIVE) {
                didActivate = true;
                dbUser.setActive(ActiveState.ACTIVE);
            }
        }

        final Query groupQuery = session.createQuery("select group from usergroup where group.groupname = :groupname");
        groupQuery.setString("groupname", "users");

        UserGroup users = null;
        final Iterator<?> grpIt = groupQuery.iterate();
        if (grpIt.hasNext() && didActivate && dbUser != null) {
            users = (UserGroup) grpIt.next();
            dbUser.getGroups().add(users);
        }

        if (dbUser != null && didActivate) {
            UserDBHelper.loginUser(dbUser, didActivate, false, userLogin, session);
        }

        trx.commit();
        session.close();

        return didActivate;
    }

    /**
     * Logs in a user returned from database after the login test was made.<br/>
     * @param dbUser the user from database (session may not be closed!)
     * @param passedLogin did the user pass the login test?
     * @param rememberMe will the user stay logged in?
     * @param userLogin the login object
     * @param session the hibernate session for further queries
     * @return true if the user was logged in
     */
    private static boolean loginUser(final User dbUser, final boolean passedLogin, final boolean rememberMe,
            final LoginBean userLogin, final Session session) {
        boolean didLogin = false;
        if (passedLogin && dbUser.getActive() == ActiveState.ACTIVE) {
            didLogin = true;
            userLogin.setUserName(dbUser.getUsername());
            userLogin.setLoggedIn(true);
        } else {
            didLogin = false;
            userLogin.setUserName(userLogin.getUserName());
            userLogin.setLoggedIn(false);
        }

        dbUser.setLastlogin(new Date());

        final UUID loginUUID = UUID.randomUUID();
        Cookie cookie = null;
        final int tenDays = 60 * 60 * 24 * 10;
        if (rememberMe && passedLogin) {
            cookie = new Cookie("login", loginUUID.toString());
            cookie.setMaxAge(tenDays);
            dbUser.setCookieid(loginUUID.toString());
        } else {
            cookie = new Cookie("login", "");
            cookie.setMaxAge(0);
            dbUser.setCookieid("");
        }

        // find permissions ...
        for (final Permission perm : dbUser.getPermissions()) {
            if (PermissionCategoryMap.CATEGORY_CONFIG.equals(perm.getCategory())) {
                userLogin.setConfigPermission(perm);
            }
        }
        for (final UserGroup grp : dbUser.getGroups()) {
            for (final Permission perm : grp.getPermissions()) {
                if (PermissionCategoryMap.CATEGORY_CONFIG.equals(perm.getCategory())) {
                    userLogin.setConfigPermission(perm);
                }
            }
        }

        final HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
        httpServletResponse.addCookie(cookie);

        return didLogin;
    }

    /**
     * Logs the user in if it exists and password matches.<br/>
     * @param username the users username
     * @param pass the password
     * @param rememberMe flag if cookie is to be set to remember the user
     * @param userLogin the login object
     * @return true if login succeeded, false if not
     */
    public static boolean loginUser(final String username, final String pass, final boolean rememberMe,
            final LoginBean userLogin) {
        final SessionFactory sf = DBSetup.getDbManager().getSessionFactory();
        final Session session = sf.openSession();
        final Transaction trx = session.beginTransaction();
        final Query query = session.createQuery("select user from User as user where user.username = :username");
        query.setString("username", username);

        User dbUser = null;
        final Iterator<?> it = query.iterate();
        if (query.iterate().hasNext()) {
            dbUser = (User) it.next();
        } else {
            dbUser = new User();
        }

        final boolean passedLogin = PasswordHashUtility.verifyPassword(pass, dbUser.getPassword(),
                Base64.decodeBase64(dbUser.getSalt()));
        final boolean didLogin = UserDBHelper.loginUser(dbUser, passedLogin, rememberMe, userLogin, session);

        trx.commit();
        session.close();

        return didLogin;
    }

    /**
     * Tries to log in a user with a cookie id.<br/>
     * @param cookieId the cookie id
     * @param userLogin the login object
     * @return true if login succeeded, false if not
     */
    public static boolean loginUserWithCookie(final String cookieId, final LoginBean userLogin) {
        final SessionFactory sf = DBSetup.getDbManager().getSessionFactory();
        final Session session = sf.openSession();
        final Transaction trx = session.beginTransaction();
        final Query query = session.createQuery("select user from User as user where user.cookieid = :cookieid");
        query.setString("cookieid", cookieId);

        User dbUser = null;
        boolean passedLogin = false;
        final Iterator<?> it = query.iterate();
        if (query.iterate().hasNext()) {
            dbUser = (User) it.next();
            passedLogin = true;
        } else {
            dbUser = new User();
            passedLogin = false;
        }

        final boolean didLogin = UserDBHelper.loginUser(dbUser, passedLogin, true, userLogin, session);

        trx.commit();
        session.close();

        return didLogin;
    }

    /**
     * Logs out the user.<br/>
     * @param userLogin the login object
     */
    public static void logoutUser(final LoginBean userLogin) {
        final SessionFactory sf = DBSetup.getDbManager().getSessionFactory();
        final Session session = sf.openSession();
        final Transaction trx = session.beginTransaction();
        final Query query = session.createQuery("select user from User as user where user.username = :username");
        query.setString("username", userLogin.getUserName());

        final Iterator<?> it = query.iterate();
        if (query.iterate().hasNext()) {
            final User dbUser = (User) it.next();
            dbUser.setCookieid("");
            session.update(dbUser);
        }

        trx.commit();
        session.close();

        userLogin.setLoggedIn(false);
        userLogin.setPassword(null);
        userLogin.setRememberMe(false);
        userLogin.setUserName(null);
        userLogin.clearPermissions();

        if (userLogin.isRememberMe()) {
            final HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            final Cookie cookie = new Cookie("login", "");
            cookie.setMaxAge(0);
            httpServletResponse.addCookie(cookie);
        }

        userLogin.setRememberMe(false);
    }

    /**
     * Initializes the database for user management.<br/>
     */
    public static void initializeDB() {
        final UserGroup admins = new UserGroup("admins");
        admins.getPermissions().add(
                new Permission("*", PermissionCategoryMap.CATEGORY_CONFIG, PermissionType.READWRITER));

        final UserGroup users = new UserGroup("users");
        // TODO: set standard user permissions

        final User admin = new User("admin", "password", "", "", "");
        admin.getGroups().add(admins);
        admin.getGroups().add(users);
        admin.getPermissions().add(new Permission("*", PermissionCategoryMap.CATEGORY_CONFIG, PermissionType.OWNER));

        admin.setActive(ActiveState.ACTIVE);
        UserDBHelper.addUserToDB(admin);

        final Timer timer = new Timer();
        final TimerTask task = new UserDBScheduler();
        task.run();
        final int onceADay = 1000 * 60 * 60 * 24;
        timer.scheduleAtFixedRate(new UserDBScheduler(), new Date(), onceADay);
    }
}
