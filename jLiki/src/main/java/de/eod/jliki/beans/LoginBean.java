/**
 * Copyright (C) ${year} The jLiki Programming Team.
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
package de.eod.jliki.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.eod.jliki.util.BeanLogger;

/**
 * This bean manages the login to the liki.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    /** holds the logger. */
    private static final BeanLogger LOGGER = new BeanLogger(LoginBean.class);
    /** true if a user is logged in. */
    private boolean isLoggedIn = false;
    /** true if login failed. */
    private boolean isLoginFailed = false;
    /** holds the username of the user logged in. */
    private String userName = "user";
    /** holds the users password. */
    private String password = null;
    /** holds the users email address. */
    private String email = "eMail";

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
     * @return the hasLoginFailed
     */
    public final boolean isLoginFailed() {
        return this.isLoginFailed;
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
     * @return the email
     */
    public final String getEmail() {
        return this.email;
    }

    /**
     * @param theEmail the email to set
     */
    public final void setEmail(final String theEmail) {
        this.email = theEmail;
    }

    /**
     * try to do the logon.<br/>
     * @return a string indicating success or failure
     */
    public final String doLogon() {
        if (this.userName == null || this.userName.trim().isEmpty()) {
            LOGGER.error("Username must not be \"null\" or empty.");
            return null;
        }

        if (this.password == null || this.password.trim().isEmpty()) {
            LOGGER.error("Password must not be \"null\" or empty.");
            return null;
        }

        if (this.userName.equals("smaisch") && this.password.equals("test")) {
            this.password = null;
            this.isLoggedIn = true;
            this.isLoginFailed = false;
            return null;
        }

        this.password = null;
        this.isLoggedIn = false;
        this.isLoginFailed = true;
        LOGGER.info("Login failed.");
        return null;
    }

    /**
     * log the user out.<br/>
     * @return a string indicating success or failure
     */
    public final String doLogout() {
        this.isLoggedIn = false;
        this.isLoginFailed = false;
        this.userName = null;
        this.password = null;
        return "logout_success";
    }

    /**
     * registers a new user.<br/>
     * @return a string indicating success of failure
     */
    public final String doRegister() {
        if (this.email == null || this.email.trim().isEmpty()) {
            LOGGER.error("eMail must not be \"null\" or empty.");
            return null;
        }

        if (!this.email.matches("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b")) {
            LOGGER.error("This is not a valid eMail address: " + this.email);
            return null;
        }
        return null;
    }
}
