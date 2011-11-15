/**
 * File: UserGroup.java
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
 * 14.11.2011: File creation.
 */
package de.eod.jliki.users.dbbeans;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

/**
 * This is a bean for the database serialization of groups of users.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
@Entity
public class UserGroup implements Serializable {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** holds the group id. */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;
    /** holds the groups name. */
    private String groupname;
    /** holds the users in the group. */
    @ManyToMany(cascade = {CascadeType.ALL })
    private Set<User> users = new HashSet<User>();
    /** holds the permissions the group has. */
    @ManyToMany(cascade = {CascadeType.ALL })
    private Set<Permission> permissions = new HashSet<Permission>();

    /**
     * Class construction.<br/>
     */
    public UserGroup() {
        this.id = 0;
        this.groupname = "";
    }

    /**
     * Class construction.<br/>
     * @param name the groups name
     */
    public UserGroup(final String name) {
        this.id = 0;
        this.groupname = name;
    }

    /**
     * getter for property id
     * @return returns the id.
    */
    public final Integer getId() {
        return this.id;
    }

    /**
     * setter for property id
     * @param theId The id to set.
     */
    public final void setId(final Integer theId) {
        this.id = theId;
    }

    /**
     * getter for property groupname
     * @return returns the groupname.
    */
    public final String getGroupname() {
        return this.groupname;
    }

    /**
     * setter for property groupname
     * @param theGroupname The groupname to set.
     */
    public final void setGroupname(final String theGroupname) {
        this.groupname = theGroupname;
    }

    /**
     * getter for property users
     * @return returns the users.
    */
    public final Set<User> getUsers() {
        return this.users;
    }

    /**
     * setter for property users
     * @param theUsers The users to set.
     */
    public final void setUsers(final Set<User> theUsers) {
        this.users = theUsers;
    }

    /**
     * getter for property permissions
     * @return returns the permissions.
    */
    public final Set<Permission> getPermissions() {
        return this.permissions;
    }

    /**
     * setter for property permissions
     * @param thePermissions The permissions to set.
     */
    public final void setPermissions(final Set<Permission> thePermissions) {
        this.permissions = thePermissions;
    }

    /**
     * @see java.lang.Object#toString()
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return MessageFormat.format("{0}: id={1}, groupname={2}", new Object[] {this.getClass().getSimpleName(),
                this.id, this.groupname });
    }
}
