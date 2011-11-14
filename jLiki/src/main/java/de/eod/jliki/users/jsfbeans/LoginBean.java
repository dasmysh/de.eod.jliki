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

import de.eod.jliki.users.dbbeans.UserDBHelper;
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

    /**
     * Class construction.<br/>
     */
    public LoginBean() {
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
     * try to do the logon.<br/>
     * @return always a null object
     */
    public final String doLogon() {
        if (!UserDBHelper.loginUser(this.userName, this.password, this.rememberMe, this)) {
            LoginBean.LOGGER.error("Login failed.");
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

        return "";
    }

    /**
     * Clears the login form data but does not change the login state.<br/>
     */
    public final void clear() {
        this.password = null;

        if (!this.isLoggedIn) {
            this.userName = null;
        }
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
