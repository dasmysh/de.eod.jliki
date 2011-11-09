/**
 * /**
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
 * 08.11.2011: File creation.
 */
package de.eod.jliki.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Size;

/**
 * Bean that manages user registration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@ManagedBean
@RequestScoped
public class UserRegisterBean implements Cloneable {

    /** holds the username. */
    // CHECKSTYLE IGNORE
    @Size(min = 3, max = 25, message = "Wrong size for username")
    private String username;
    /** holds the password. */
    private String password;
    /** holds the password confirm. */
    private String confirm;
    /** holds the users email. */
    private String email;
    /** holds the users first name. */
    private String firstname;
    /** holds the users last name. */
    private String lastname;

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

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    protected final Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
