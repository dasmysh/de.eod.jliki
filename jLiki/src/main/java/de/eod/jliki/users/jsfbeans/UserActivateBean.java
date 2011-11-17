/**
 * File: UserActivateBean.java
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
 * 12.11.2011: File creation.
 */
package de.eod.jliki.users.jsfbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import de.eod.jliki.users.utils.UserDBHelper;

/**
 * JSF Bean for checking the user activation.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@ManagedBean(name = "activation")
@RequestScoped
public class UserActivateBean {

    /** holds the logger. */
    private static final Logger LOGGER = Logger.getLogger(UserActivateBean.class);
    /** holds the activation key. */
    @ManagedProperty(value = "#{param.key}")
    private String key;
    /** holds the username. */
    @ManagedProperty(value = "#{param.user}")
    private String username;
    /** holds if the activation was valid. */
    private boolean valid;
    /** holds the login bean. */
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    /**
     * Evaluates the acitvation key and username.<br/>
     */
    @PostConstruct
    public final void init() {
        UserActivateBean.LOGGER.debug("Starting activation for user " + this.username);
        if (UserDBHelper.activateUser(this.username, this.key, this.loginBean)) {
            this.valid = true;
        }
    }

    /**
     * getter for property key
     * @return returns the key.
    */
    public final String getKey() {
        return this.key;
    }

    /**
     * setter for property key
     * @param theKey The key to set.
     */
    public final void setKey(final String theKey) {
        this.key = theKey;
    }

    /**
     * getter for property username
     * @return returns the username.
    */
    public final String getUsername() {
        return this.username;
    }

    /**
     * setter for property username
     * @param theUsername The username to set.
     */
    public final void setUsername(final String theUsername) {
        this.username = theUsername;
    }

    /**
     * getter for property valid
     * @return returns the valid.
    */
    public final boolean isValid() {
        return this.valid;
    }

    /**
     * setter for property valid
     * @param theValid The valid to set.
     */
    public final void setValid(final boolean theValid) {
        this.valid = theValid;
    }

    /**
     * setter for property loginBean
     * @param theLoginBean The loginBean to set.
     */
    public final void setLoginBean(final LoginBean theLoginBean) {
        this.loginBean = theLoginBean;
    }

}
