/**
 * File: PermissionHolder.java
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
 * 19.11.2011: File creation.
 */
package de.eod.jliki.users.dbbeans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import de.eod.jliki.users.dbbeans.Permission.PermissionType;
import de.eod.jliki.users.jsfbeans.LoginBean;
import de.eod.jliki.users.utils.PermissionCategoryMap;

/**
 * Base class for all classes the contain permissions.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PermissionHolder implements Serializable {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** holds the holders id. */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;
    /** holds the users name. */
    private String name;
    /** holds the permissions the user has. */
    @ManyToMany(cascade = {CascadeType.ALL })
    private List<Permission> permissions = new LinkedList<Permission>();

    /**
     * Class construction.<br/>
     */
    public PermissionHolder() {
        this.id = 0;
        this.name = "";
    }

    /**
     * Class construction.<br/>
     * @param theName the holders name
     */
    public PermissionHolder(final String theName) {
        this.id = 0;
        this.name = theName;
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
     * getter for property permissions
     * @return returns the permissions.
    */
    public final List<Permission> getPermissions() {
        return this.permissions;
    }

    /**
     * setter for property permissions
     * @param thePermissions The permissions to set.
     */
    public final void setPermissions(final List<Permission> thePermissions) {
        this.permissions = thePermissions;
    }

    /**
     * getter for property name
     * @return returns the name.
    */
    public final String getName() {
        return this.name;
    }

    /**
     * setter for property name
     * @param theName The name to set.
     */
    public final void setName(final String theName) {
        this.name = theName;
    }

    /**
     * Adds a permission to the user.<br/>
     * @param permission the permission to add
     */
    public final void addPermission(final Permission permission) {
        if (permission == null) {
            throw new NullPointerException("Permission may not be null!");
        }

        final Iterator<Permission> it = this.permissions.iterator();
        while (it.hasNext()) {
            final Permission listedPerm = it.next();
            if (permission.equals(listedPerm)) {
                if (permission.getType().equals(listedPerm.getType())) {
                    return;
                }

                // remove user from old permission
                listedPerm.getHolders().remove(this);
                // delete old permission
                it.remove();

                if (permission.getType() != PermissionType.NOTHING) {
                    // add user to permission
                    permission.getHolders().add(this);
                    // add new permission
                    this.permissions.add(permission);
                }

                return;
            }
        }

        if (permission.getType() != PermissionType.NOTHING) {
            permission.getHolders().add(this);
            this.permissions.add(permission);
        }
    }

    /**
     * Removes a permission from the user.<br/>
     * @param permission the permission to remove
     */
    public final void removePermission(final Permission permission) {
        if (permission == null) {
            throw new NullPointerException("Permission may not be null!");
        }

        final Iterator<Permission> it = this.permissions.iterator();
        while (it.hasNext()) {
            final Permission listedPerm = it.next();
            if (permission.equals(listedPerm)) {
                listedPerm.getHolders().remove(this);
                it.remove();
            }
        }
    }

    /**
     * Transfers all permissions to a login bean.<br/>
     * @param login the login bean
     */
    public final void transferPermissionsToLogin(final LoginBean login) {
        for (final Permission perm : this.permissions) {
            if (PermissionCategoryMap.CATEGORY_CONFIG.equals(perm.getCategory())) {
                login.setConfigPermission(perm);
            } else if (PermissionCategoryMap.CATEGORY_DOCROOT.equals(perm.getCategory())) {
                login.setDocrootPermission(perm);
            } else if (PermissionCategoryMap.CATEGORY_FILES.equals(perm.getCategory())) {
                login.setFilePermission(perm);
            }
        }
    }
}
