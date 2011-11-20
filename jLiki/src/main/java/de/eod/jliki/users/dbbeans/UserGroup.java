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
import javax.persistence.ManyToMany;

/**
 * This is a bean for the database serialization of groups of users.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
@Entity
public class UserGroup extends PermissionHolder implements Serializable {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** holds the users in the group. */
    @ManyToMany(cascade = {CascadeType.ALL })
    private Set<User> users = new HashSet<User>();

    /**
     * Class construction.<br/>
     */
    public UserGroup() {
        super();
    }

    /**
     * Class construction.<br/>
     * @param groupname the groups name
     */
    public UserGroup(final String groupname) {
        super(groupname);
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
     * @see java.lang.Object#toString()
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return MessageFormat.format("{0}: id={1}, groupname={2}",
                new Object[] {this.getClass().getSimpleName(), this.getId(), this.getName() });
    }
}
