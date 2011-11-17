/**
 * File: Permission.java
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
 * 15.11.2011: File creation.
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
 * This is a bean for the database serialization of permissions.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
@Entity
public class Permission implements Serializable {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** enumerates the possible permission types. */
    public enum PermissionType {
        /** user can do nothing (just an initial value, not really used ...). */
        NOTHING,
        /** user can read the values defined by the permission. */
        READER,
        /** user can write the values (dont know if this is needed, just to be sure ...). */
        WRITER,
        /** user can read and write the values. */
        READWRITER,
        /** user owns the values (may set permissions for other users). */
        OWNER
    }

    /** holds the permission id. */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;
    /** holds the permissions name. */
    private String permissionName;
    /** holds the permissions category. */
    private String category;
    /** holds the permissions type. */
    private PermissionType type;
    /** holds the users with this permission. */
    @ManyToMany(cascade = {CascadeType.ALL })
    private Set<User> users = new HashSet<User>();
    /** holds the groups with this permission. */
    @ManyToMany(cascade = {CascadeType.ALL })
    private Set<UserGroup> groups = new HashSet<UserGroup>();

    /**
     * Class construction.<br/>
     */
    public Permission() {
        this.id = 0;
        this.permissionName = "";
        this.category = "";
        this.type = PermissionType.NOTHING;
    }

    /**
     * Class construction.<br/>
     * @param name the permissions name
     * @param theCategory the permissions category
     * @param theType the permissions type
     */
    public Permission(final String name, final String theCategory, final PermissionType theType) {
        this.id = 0;
        this.permissionName = name;
        this.category = theCategory;
        this.type = theType;
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
     * getter for property permissionName
     * @return returns the permissionName.
    */
    public final String getPermissionName() {
        return this.permissionName;
    }

    /**
     * setter for property permissionName
     * @param thePermissionName The permissionName to set.
     */
    public final void setPermissionName(final String thePermissionName) {
        this.permissionName = thePermissionName;
    }

    /**
     * getter for property category
     * @return returns the category.
    */
    public final String getCategory() {
        return this.category;
    }

    /**
     * setter for property category
     * @param theCategory The category to set.
     */
    public final void setCategory(final String theCategory) {
        this.category = theCategory;
    }

    /**
     * getter for property type
     * @return returns the type.
    */
    public final PermissionType getType() {
        return this.type;
    }

    /**
     * setter for property type
     * @param theType The type to set.
     */
    public final void setType(final PermissionType theType) {
        this.type = theType;
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
        return MessageFormat.format("{0}: id={1}, permissionName={2}", new Object[] {this.getClass().getSimpleName(),
                this.id, this.permissionName });
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof Permission)) {
            return false;
        }

        final Permission var = (Permission) obj;

        return var.getCategory().equals(this.category) && var.getPermissionName().equals(this.permissionName);
    }

    /**
     * @see java.lang.Object#hashCode()
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        final int startValue = 13;
        final int multi = 43;
        int hashCode = startValue + this.category == null ? 0 : this.category.hashCode();
        hashCode = hashCode * multi + (this.permissionName == null ? 0 : this.permissionName.hashCode());
        return hashCode;
    }
}
