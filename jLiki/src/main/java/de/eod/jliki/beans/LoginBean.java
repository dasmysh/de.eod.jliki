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
package de.eod.jliki.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.validator.constraints.NotBlank;

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
     * try to do the logon.<br/>
     * @return always a null object
     */
    public final String doLogon() {
        if (this.userName.equals("smaisch") && this.password.equals("test")) {
            this.password = null;
            this.isLoggedIn = true;
            return null;
        }

        this.password = null;
        this.isLoggedIn = false;
        LOGGER.info("Login failed.");
        return null;
    }

    /**
     * log the user out.<br/>
     * @return a string indicating success or failure
     */
    public final String doLogout() {
        this.isLoggedIn = false;
        this.userName = null;
        this.password = null;
        return "";
    }
}
