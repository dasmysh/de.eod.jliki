/**
 * File: PermissionTypeBean.java
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
package de.eod.jliki.users.jsfbeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.eod.jliki.users.dbbeans.Permission.PermissionType;

/**
 * Helper bean to visualize {@link PermissionType} values in jsf.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
@ManagedBean
@ApplicationScoped
public class PermissionTypeBean implements Serializable {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Returns an array of all {@link PermissionType} values.<br/>
     * @return the array of values
     */
    public final PermissionType[] getPermissionTypes() {
        return PermissionType.values();
    }

    /**
     * Returns the ordinal number of a {@link PermissionType}.<br/>
     * @param type the type to get the number from
     * @return the types ordinal
     */
    public final int ordinal(final PermissionType type) {
        return type.ordinal();
    }

    /**
     * Checks if the permission allows reading.<br/>
     * @param type the permission type to test
     * @return true if reading is allowed
     */
    public final boolean canRead(final PermissionType type) {
        if (type == PermissionType.READER || type.ordinal() >= PermissionType.READWRITER.ordinal()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the permission allows writing.<br/>
     * @param type the permission type to test
     * @return true if writing is allowed
     */
    public final boolean canWrite(final PermissionType type) {
        return type.ordinal() >= PermissionType.WRITER.ordinal();
    }

    /**
     * Checks if the permission allows owning.<br/>
     * @param type the permission type to test
     * @return true if the permission says owner
     */
    public final boolean isOwner(final PermissionType type) {
        return type == PermissionType.OWNER;
    }
}
