/**
 * File: User.java
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
 * 10.11.2011: File creation.
 */
package de.eod.jliki.users.dbbeans;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.codec.binary.Base64;

import de.eod.jliki.users.utils.PasswordHashUtility;

/**
 * This is the bean for database serialization of users.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@Entity
public class User extends PermissionHolder implements Serializable {

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
    private Date lastlogin;
    /** holds the users login cookie id. */
    private String cookieid;
    /** holds the groups the user is in. */
    @ManyToMany(cascade = {CascadeType.ALL })
    private Set<UserGroup> groups = new HashSet<UserGroup>();

    /**
     * Creates an user object.<br/>
     */
    public User() {
        super();
        this.password = "";
        this.salt = "";
        this.email = "";
        this.firstname = "";
        this.lastname = "";
        this.active = ActiveState.INACTIVE;
        this.registerdate = new Date();
        this.lastlogin = new Date();
        this.cookieid = "";
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
        super(theUsername);
        this.email = theEmail;
        this.firstname = theFirstname;
        this.lastname = theLastname;
        this.active = ActiveState.INACTIVE;
        this.registerdate = new Date();
        this.lastlogin = new Date();
        this.cookieid = "";

        final byte[] byteSalt = PasswordHashUtility.generateSalt();
        this.password = PasswordHashUtility.hashPassword(thePassword, byteSalt);
        this.salt = Base64.encodeBase64String(byteSalt);
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
     * @return the lastlogin
     */
    public final Date getLastlogin() {
        return this.lastlogin;
    }

    /**
     * @param theLastlogin the lastlogin to set
     */
    public final void setLastlogin(final Date theLastlogin) {
        this.lastlogin = theLastlogin;
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

    /**
     * getter for property cookieid
     * @return returns the cookieid.
    */
    public final String getCookieid() {
        return this.cookieid;
    }

    /**
     * setter for property cookieid
     * @param theCookieId The cookieid to set.
     */
    public final void setCookieid(final String theCookieId) {
        this.cookieid = theCookieId;
    }

    /**
     * getter for property groups
     * @return returns the groups.
    */
    public final Set<UserGroup> getGroups() {
        return this.groups;
    }

    /**
     * setter for property groups
     * @param theGroups The groups to set.
     */
    public final void setGroups(final Set<UserGroup> theGroups) {
        this.groups = theGroups;
    }

    /**
     * @see java.lang.Object#toString()
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return MessageFormat.format("{0}: id={1}, username={2}, password={3}, salt={4}, email={5}, fistname={6},"
                + " lastname={7}, active={8}, registerdate={9}, lastlogin={10}, salt={11}, cookieid={12}",
                new Object[] {this.getClass().getSimpleName(), this.getId(), this.getName(), this.password, this.salt,
                        this.email, this.firstname, this.lastname, this.active, this.registerdate, this.lastlogin,
                        this.salt, this.cookieid });
    }
}
