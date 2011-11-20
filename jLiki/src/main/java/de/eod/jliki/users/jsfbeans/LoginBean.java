/**
 * File: LoginBean.java
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
 * 06.11.2011: File creation.
 */
package de.eod.jliki.users.jsfbeans;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import org.hibernate.validator.constraints.NotBlank;

import de.eod.jliki.config.ConfigManager;
import de.eod.jliki.users.dbbeans.Permission;
import de.eod.jliki.users.dbbeans.Permission.PermissionType;
import de.eod.jliki.users.utils.PermissionCategoryMap;
import de.eod.jliki.users.utils.UserDBHelper;
import de.eod.jliki.util.BeanLogger;

/**
 * This bean manages the login to the liki.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    /** holds serialization UID. */
    private static final long serialVersionUID = 1L;
    /** holds the logger. */
    private static final BeanLogger LOGGER = new BeanLogger(LoginBean.class);
    /** true if a user is logged in. */
    private boolean isLoggedIn = false;
    /** holds the username of the user logged in. */
    @NotBlank(message = "Username may not be blank!")
    private String userName = "user";
    /** holds the users password. */
    @NotBlank(message = "Password may not be blank!")
    private String password = null;
    /** holds the remember me flag. */
    private boolean rememberMe = false;
    /** holds the highest permission for configuration the user has. */
    private PermissionType configPermission = PermissionType.NOTHING;
    /** holds the map with all configuration related permissions. */
    private final PermissionCategoryMap configPermissions;
    /** holds the highest permission for docroot the user has. */
    private PermissionType docrootPermission;
    /** holds the map with all docroot related permissions. */
    private final PermissionCategoryMap docrootPermissions;
    /** holds the map with all files related permissions. */
    private final PermissionCategoryMap filePermissions;

    /**
     * Class construction.<br/>
     */
    public LoginBean() {
        this.configPermissions = new PermissionCategoryMap(PermissionCategoryMap.CATEGORY_CONFIG);
        this.docrootPermissions = new PermissionCategoryMap(PermissionCategoryMap.CATEGORY_DOCROOT);
        this.filePermissions = new PermissionCategoryMap(PermissionCategoryMap.CATEGORY_FILES);
        this.clearPermissions();

        this.checkCookie();
    }

    /**
     * @return the password
     */
    public final String getPassword() {
        return this.password;
    }

    /**
     * @param thePassword the password to set
     */
    public final void setPassword(final String thePassword) {
        this.password = thePassword;
    }

    /**
     * @return the isLoggedIn
     */
    public final boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    /**
     * setter for property isLoggedIn
     * @param loggedIn The isLoggedIn to set.
     */
    public final void setLoggedIn(final boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }

    /**
     * @return the userName
     */
    public final String getUserName() {
        return this.userName;
    }

    /**
     * @param theUserName the userName to set
     */
    public final void setUserName(final String theUserName) {
        this.userName = theUserName;
    }

    /**
     * getter for property rememberMe
     * @return returns the rememberMe.
    */
    public final boolean isRememberMe() {
        return this.rememberMe;
    }

    /**
     * setter for property rememberMe
     * @param theRememberMe The rememberMe to set.
     */
    public final void setRememberMe(final boolean theRememberMe) {
        this.rememberMe = theRememberMe;
    }

    /**
     * getter for property configPermission
     * @return returns the configPermission.
    */
    public final PermissionType getConfigPermission() {
        return this.configPermission;
    }

    /**
     * setter for property configPermission
     * @param theConfigPermission The configPermission to set.
     */
    public final void setConfigPermission(final Permission theConfigPermission) {
        this.configPermission = this.configPermissions.put(theConfigPermission);
    }

    /**
     * getter for property configPermissions
     * @return returns the configPermissions.
    */
    public final PermissionCategoryMap getConfigPermissions() {
        return this.configPermissions;
    }

    /**
     * getter for property docrootPermission
     * @return returns the docrootPermission.
    */
    public final PermissionType getDocrootPermission() {
        return this.docrootPermission;
    }

    /**
     * setter for property docrootPermission
     * @param theDocrootPermission The docrootPermission to set.
     */
    public final void setDocrootPermission(final Permission theDocrootPermission) {
        this.docrootPermission = this.docrootPermissions.put(theDocrootPermission);
    }

    /**
     * getter for property docrootPermissions
     * @return returns the docrootPermissions.
    */
    public final PermissionCategoryMap getDocrootPermissions() {
        return this.docrootPermissions;
    }

    /**
     * getter for property filePermissions
     * @return returns the filePermissions.
    */
    public final PermissionCategoryMap getFilePermissions() {
        return this.filePermissions;
    }

    /**
     * setter for property filePermission
     * @param theFilePermission The filePermissions to set.
     */
    public final void setFilePermission(final Permission theFilePermission) {
        this.filePermissions.put(theFilePermission);
    }

    /**
     * Returns if the configuration dialog is viewed or not.<br/>
     * @return true if the config dialog is to be shown
     */
    //public final boolean getViewConfigDlg() {
    //    return this.configPermission.ordinal() >= PermissionType.READER.ordinal();
    //}

    /**
     * Clears the config bean.<br/>
     */
    public final void clear() {
        this.password = null;

        if (!this.isLoggedIn) {
            this.userName = null;
            this.clearPermissions();
        }
    }

    /**
     * Clears all permission this bean has.<br/>
     */
    public final void clearPermissions() {
        this.configPermission = PermissionType.NOTHING;
        this.configPermissions.clear();

        this.docrootPermissions.clear();
        this.docrootPermission = this.docrootPermissions.put(ConfigManager.getInstance().getConfig().getBaseConfig()
                .pubCompiledDocPermission());

        this.filePermissions.clear();
        this.filePermissions.put(ConfigManager.getInstance().getConfig().getBaseConfig().pubFilePermission());
    }

    /**
     * try to do the logon.<br/>
     * @return always a null object
     */
    public final String doLogon() {
        this.clearPermissions();

        if (!UserDBHelper.loginUser(this.userName, this.password, this.rememberMe, this)) {
            LoginBean.LOGGER.error("Login failed.");
            this.password = null;
            return null;
        }

        this.password = null;
        LoginBean.LOGGER.debug("User \"" + this.userName + "\" logged in.");

        return null;
    }

    /**
     * log the user out.<br/>
     * @return a string indicating success or failure
     */
    public final String doLogout() {
        UserDBHelper.logoutUser(this);
        return null;
    }

    /**
     * Checks for the login cookie and logs the user in if the cookie exists.<br/>
     */
    private void checkCookie() {
        final Map<String, Object> cookies = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestCookieMap();

        final Cookie cookie = (Cookie) cookies.get("login");
        if (cookie != null) {
            UserDBHelper.loginUserWithCookie(cookie.getValue(), this);
        }

    }
}
