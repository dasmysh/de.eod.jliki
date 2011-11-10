/**
 * Copyright (C) 2011 The jLiki Programming Team.
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
 * 10.11.2011: File creation.
 */
package de.eod.jliki.db.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.annotations.GenericGenerator;

import de.eod.jliki.util.PasswordHashUtility;

/**
 * This is the bean for database serialization of users.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@Entity
public class User implements Serializable {

    /** holds the serialization UID. */
    private static final long serialVersionUID = 1L;

    /** enumerates the possible active states for a user. */
    public enum ActiveState {
        /** The account was not activated yet. */
        INACTIVE,
        /** The account is active. */
        ACTIVE,
        /** The users account was locked. */
        LOCKED
    }

    /** holds the id. */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;
    /** holds the users name. */
    private String username;
    /** holds the users password. */
    private String password;
    /** holds the passwords salt. */
    private String salt;
    /** holds the users email address. */
    private String email;
    /** holds the users first name. */
    private String firstname;
    /** holds the users last name. */
    private String lastname;
    /** holds the users active state. */
    @Enumerated(value = EnumType.ORDINAL)
    private ActiveState active;
    /** holds the users registration date. */
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date registerdate;
    /** holds the date the user last logged in. */
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastloggin;

    /**
     * Creates an user object.<br/>
     */
    public User() {
        this.id = 0;
        this.username = "";
        this.password = "";
        this.salt = "";
        this.email = "";
        this.firstname = "";
        this.lastname = "";
        this.active = ActiveState.INACTIVE;
        this.registerdate = new Date();
        this.lastloggin = new Date();
    }

    /**
     * Creates a new user.<br/>
     * @param theUsername the users internal name
     * @param thePassword the users password (unhashed!)
     * @param theEmail the users email
     * @param theFirstname the users first name
     * @param theLastname the users last name
     */
    public User(final String theUsername, final String thePassword, final String theEmail, final String theFirstname,
            final String theLastname) {
        this.id = 0;
        this.username = theUsername;
        this.email = theEmail;
        this.firstname = theFirstname;
        this.lastname = theLastname;
        this.active = ActiveState.INACTIVE;
        this.registerdate = new Date();
        this.lastloggin = new Date();

        final byte[] byteSalt = PasswordHashUtility.generateSalt();
        this.password = PasswordHashUtility.hashPassword(thePassword, byteSalt);
        this.salt = Base64.encodeBase64String(byteSalt);
    }

    /**
     * @return the id
     */
    public final Integer getId() {
        return this.id;
    }

    /**
     * @param theId the id to set
     */
    public final void setId(final Integer theId) {
        this.id = theId;
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
     * @return the active
     */
    public final ActiveState getActive() {
        return this.active;
    }

    /**
     * @param theActive the active to set
     */
    public final void setActive(final ActiveState theActive) {
        this.active = theActive;
    }

    /**
     * @return the registerdate
     */
    public final Date getRegisterdate() {
        return this.registerdate;
    }

    /**
     * @param theRegisterdate the registerdate to set
     */
    public final void setRegisterdate(final Date theRegisterdate) {
        this.registerdate = theRegisterdate;
    }

    /**
     * @return the lastloggin
     */
    public final Date getLastloggin() {
        return this.lastloggin;
    }

    /**
     * @param theLastloggin the lastloggin to set
     */
    public final void setLastloggin(final Date theLastloggin) {
        this.lastloggin = theLastloggin;
    }

    /**
     * @return the salt
     */
    public final String getSalt() {
        return this.salt;
    }

    /**
     * @param theSalt the salt to set
     */
    public final void setSalt(final String theSalt) {
        this.salt = theSalt;
    }
}
