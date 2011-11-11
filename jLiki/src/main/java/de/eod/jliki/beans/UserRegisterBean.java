/**
 * File: UserRegisterBean.java
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
 * 08.11.2011: File creation.
 */
package de.eod.jliki.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import de.eod.jliki.db.DBSetup;
import de.eod.jliki.db.beans.User;
import de.eod.jliki.util.Messages;

/**
 * Bean that manages user registration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@ManagedBean(name = "userRegisterBean")
@RequestScoped
public class UserRegisterBean implements Cloneable, Serializable {

    /** holds default serialization UID. */
    private static final long serialVersionUID = 1L;

    /** holds the username. */
    // CHECKSTYLE IGNORE
    @Size(min = 3, max = 25, message = "{register.panel.invalid.username}")
    private String username;
    /** holds the password. */
    // CHECKSTYLE IGNORE
    @Size(min = 6, max = 50, message = "{register.panel.invalid.password}")
    private String password;
    /** holds the password confirm. */
    // CHECKSTYLE IGNORE
    @Size(min = 6, max = 50, message = "{register.panel.invalid.password}")
    private String confirm;
    /** holds the users email. */
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$",
            message = "{register.panel.invalid.email}")
    private String email;
    /** holds the users first name. */
    @NotBlank(message = "{register.panel.invalid.firstname}")
    private String firstname;
    /** holds the users last name. */
    @NotBlank(message = "{register.panel.invalid.lastname}")
    private String lastname;

    /**
     * Makes sure password and confirm are the same.<br/>
     * @return true if password and confirm match
     */
    @AssertTrue(message = "{register.panel.invalid.confirm.password}")
    public final boolean isPasswordsEquals() {
        return this.password.equals(this.confirm);
    }

    /**
     * @return the username
     */
    public final String getUsername() {
        return this.username;
    }

    /**
     * @param theUsername the username to set
     */
    public final void setUsername(final String theUsername) {
        this.username = theUsername;
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
     * @return the confirm
     */
    public final String getConfirm() {
        return this.confirm;
    }

    /**
     * @param theConfirm the confirm to set
     */
    public final void setConfirm(final String theConfirm) {
        this.confirm = theConfirm;
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
     * @return the firstname
     */
    public final String getFirstname() {
        return this.firstname;
    }

    /**
     * @param theFirstname the firstname to set
     */
    public final void setFirstname(final String theFirstname) {
        this.firstname = theFirstname;
    }

    /**
     * @return the lastname
     */
    public final String getLastname() {
        return this.lastname;
    }

    /**
     * @param theLastname the lastname to set
     */
    public final void setLastname(final String theLastname) {
        this.lastname = theLastname;
    }

    /**
     * @see java.lang.Object#clone()
     * {@inheritDoc}
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Adds a new user to the jLiki database.<br/>
     */
    public final void addNewUser() {
        final User newUser = new User(this.username, this.password, this.email,
                this.firstname, this.lastname);
        DBSetup.getDbManager().saveObject(newUser);

        Messages.addFacesMessage(null, FacesMessage.SEVERITY_INFO, "message.user.registered", this.username);
    }
}
