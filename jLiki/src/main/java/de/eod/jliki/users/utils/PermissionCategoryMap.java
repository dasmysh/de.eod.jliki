/**
 * File: PermissionCategoryMap.java
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
 * 17.11.2011: File creation.
 */
package de.eod.jliki.users.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.eod.jliki.users.dbbeans.Permission;
import de.eod.jliki.users.dbbeans.Permission.PermissionType;

/**
 * Specialization of a {@link HashMap} for holding all permissions of a category.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
public class PermissionCategoryMap extends HashMap<String, PermissionType> {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** holds the configuration category name. */
    public static final String CATEGORY_CONFIG = "config";
    /** holds the document root category name. */
    public static final String CATEGORY_DOCROOT = "docroot";
    /** holds the files category name. */
    public static final String CATEGORY_FILES = "files";

    /** holds the categories name of this map. */
    private final String category;
    /** holds the currently highest permission in this map. */
    private PermissionType highestPerm = PermissionType.NOTHING;
    /** holds the "for all" permission. */
    private PermissionType forAllPerm = PermissionType.NOTHING;

    /**
     * Class construction.<br/>
     * @param theCategory the category name of this map
     */
    public PermissionCategoryMap(final String theCategory) {
        super();
        this.category = theCategory;
    }

    /**
     * @see java.util.HashMap#clear()
     * {@inheritDoc}
     */
    @Override
    public final void clear() {
        this.highestPerm = PermissionType.NOTHING;
        this.forAllPerm = PermissionType.NOTHING;
        super.clear();
    }

    /**
     * @see java.util.HashMap#get(java.lang.Object)
     * {@inheritDoc}
     */
    @Override
    public final PermissionType get(final Object key) {
        final PermissionType result = super.get(key);
        if (result == null) {
            return this.forAllPerm;
        }
        return PermissionCategoryMap.getMaxPermission(result, this.forAllPerm);
    }

    /**
     * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
     * {@inheritDoc}
     */
    @Override
    public final PermissionType put(final String key, final PermissionType value) {
        final PermissionType newValue = PermissionCategoryMap.getMaxPermission(value, this.forAllPerm);
        if (newValue.ordinal() <= this.forAllPerm.ordinal()) {
            return super.remove(key);
        }
        return super.put(key, value);
    }

    /**
     * @see java.util.HashMap#putAll(java.util.Map)
     * {@inheritDoc}
     */
    @Override
    public final void putAll(final Map<? extends String, ? extends PermissionType> m) {
        for (final java.util.Map.Entry<? extends String, ? extends PermissionType> entry : m.entrySet()) {
            this.put(new Permission(entry.getKey(), this.category, entry.getValue()));
        }
    }

    /**
     * Puts a permission in the map.<br/>
     * @param permission the permission to put into the map
     * @return the new highest permission (or {@link PermissionType.NOTHING} if category does not match)
     */
    public final PermissionType put(final Permission permission) {
        if (!this.category.equals(permission.getCategory())) {
            return PermissionType.NOTHING;
        }

        if ("*".equals(permission.getPermissionName())) {
            this.addPermissionToAll(permission.getType());
            return this.highestPerm;
        }

        PermissionType newPerm = PermissionCategoryMap.getMaxPermission(this.get(permission.getPermissionName()),
                permission.getType());
        newPerm = PermissionCategoryMap.getMaxPermission(this.forAllPerm, newPerm);
        this.put(permission.getPermissionName(), newPerm);
        this.highestPerm = PermissionCategoryMap.getMaxPermission(this.highestPerm, newPerm);
        return this.highestPerm;
    }

    /**
     * Returns a list of permission names with a permission equal or higher as the given.<br/>
     * @param type the permission type to test against
     * @return the list of names
     */
    public final List<String> getAllWithType(final PermissionType type) {
        final List<String> result = new LinkedList<String>();
        if (this.highestPerm.ordinal() < type.ordinal()) {
            return result;
        }

        if (this.forAllPerm.ordinal() < type.ordinal()
                || (type == PermissionType.READER && this.forAllPerm == PermissionType.WRITER)) {
            for (final Map.Entry<String, PermissionType> entry : this.entrySet()) {
                if (entry.getValue().ordinal() < type.ordinal()
                        || (type == PermissionType.READER && entry.getValue() == PermissionType.WRITER)) {
                    continue;
                }
                result.add(entry.getKey());
            }
            return result;
        }

        result.add("*");
        return result;
    }

    /**
     * Adds a permission to all entries in this map.<br/>
     * @param perm the permission to add
     */
    private void addPermissionToAll(final PermissionType perm) {
        this.forAllPerm = PermissionCategoryMap.getMaxPermission(this.forAllPerm, perm);
        this.highestPerm = this.forAllPerm;
        for (final Map.Entry<String, PermissionType> entry : this.entrySet()) {
            this.put(entry.getKey(), PermissionCategoryMap.getMaxPermission(this.forAllPerm, entry.getValue()));
            this.highestPerm = PermissionCategoryMap.getMaxPermission(this.highestPerm, entry.getValue());
        }
    }

    /**
     * Compares the two permission types and returns the highest one combining the two.<br/>
     * @param type1 the first type
     * @param type2 the second type
     * @return the combined permission type
     */
    private static PermissionType getMaxPermission(final PermissionType type1, final PermissionType type2) {
        if (type1 == PermissionType.READER && type2 == PermissionType.WRITER || type1 == PermissionType.WRITER
                && type2 == PermissionType.READER) {
            return PermissionType.READWRITER;
        }

        if (type1.ordinal() >= type2.ordinal()) {
            return type1;
        } else {
            return type2;
        }
    }
}
