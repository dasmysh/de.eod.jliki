/**
 * File: BaseConfigBean.java
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
package de.eod.jliki.config;

import java.io.Serializable;

import de.eod.jliki.users.dbbeans.Permission.PermissionType;

/**
 * The jLikis base configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
public class BaseConfig implements Serializable {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** holds the documents base directory. */
    private String docBasedir;
    /** holds the number of minutes files get locked. */
    private int fileLockTime;
    /** holds the number of recent changes to display. */
    private int numRecentChanges;
    /** holds the standard permission a user has on the document root. */
    private PermissionType userDocrootPermission;
    /** holds the standard permission a user has on each file. */
    private PermissionType userFilePermission;
    /** holds the standard permission the public has on a compiled document. */
    private PermissionType pubCompiledDocPermission;
    /** holds the standard permission the public has on each file. */
    private PermissionType pubFilePermission;

    /**
     * Class construction.<br/>
     */
    public BaseConfig() {
    }

    /**
     * Class construction.<br/>
     * @param theDocBasedir the document base directory
     * @param theFileLockTime the file lock time
     * @param theNumRecentChanged the max number of recent changes
     * @param theUserDocrootPermission the standard user docroot permission
     * @param theUserFilePermission the standard user permission for every file
     * @param thePubCompiledDocPermission the permission on compiled documents for public users
     * @param thePubFilePermission the permission on files for public users
     */
    public BaseConfig(final String theDocBasedir, final int theFileLockTime, final int theNumRecentChanged,
            final PermissionType theUserDocrootPermission, final PermissionType theUserFilePermission,
            final PermissionType thePubCompiledDocPermission, final PermissionType thePubFilePermission) {
        this.docBasedir = theDocBasedir;
        this.fileLockTime = theFileLockTime;
        this.numRecentChanges = theNumRecentChanged;
        this.userDocrootPermission = theUserDocrootPermission;
        this.userFilePermission = theUserFilePermission;
        this.pubCompiledDocPermission = thePubCompiledDocPermission;
        this.pubFilePermission = thePubFilePermission;
    }

    /**
     * getter for property docBasedir
     * @return returns the docBasedir.
    */
    public final String getDocBasedir() {
        return this.docBasedir;
    }

    /**
     * setter for property docBasedir
     * @param theDocBasedir The docBasedir to set.
     */
    public final void setDocBasedir(final String theDocBasedir) {
        this.docBasedir = theDocBasedir;
    }

    /**
     * getter for property fileLockTime
     * @return returns the fileLockTime.
    */
    public final int getFileLockTime() {
        return this.fileLockTime;
    }

    /**
     * setter for property fileLockTime
     * @param theFileLockTime The fileLockTime to set.
     */
    public final void setFileLockTime(final int theFileLockTime) {
        this.fileLockTime = theFileLockTime;
    }

    /**
     * getter for property numRecentChanges
     * @return returns the numRecentChanges.
    */
    public final int getNumRecentChanges() {
        return this.numRecentChanges;
    }

    /**
     * setter for property numRecentChanges
     * @param theNumRecentChanges The numRecentChanges to set.
     */
    public final void setNumRecentChanges(final int theNumRecentChanges) {
        this.numRecentChanges = theNumRecentChanges;
    }

    /**
     * getter for property userDocrootPermission
     * @return returns the userDocrootPermission.
    */
    public final PermissionType getUserDocrootPermission() {
        return this.userDocrootPermission;
    }

    /**
     * setter for property userDocrootPermission
     * @param theUserDocrootPermission The userDocrootPermission to set.
     */
    public final void setUserDocrootPermission(final PermissionType theUserDocrootPermission) {
        this.userDocrootPermission = theUserDocrootPermission;
    }

    /**
     * getter for property userFilePermission
     * @return returns the userFilePermission.
    */
    public final PermissionType getUserFilePermission() {
        return this.userFilePermission;
    }

    /**
     * setter for property userFilePermission
     * @param theUserFilePermission The userFilePermission to set.
     */
    public final void setUserFilePermission(final PermissionType theUserFilePermission) {
        this.userFilePermission = theUserFilePermission;
    }

    /**
     * getter for property pubCompiledDocPermission
     * @return returns the pubCompiledDocPermission.
    */
    public final PermissionType getPubCompiledDocPermission() {
        return this.pubCompiledDocPermission;
    }

    /**
     * setter for property pubCompiledDocPermission
     * @param thePubCompiledDocPermission The pubCompiledDocPermission to set.
     */
    public final void setPubCompiledDocPermission(final PermissionType thePubCompiledDocPermission) {
        this.pubCompiledDocPermission = thePubCompiledDocPermission;
    }

    /**
     * getter for property pubFilePermission
     * @return returns the pubFilePermission.
    */
    public final PermissionType getPubFilePermission() {
        return this.pubFilePermission;
    }

    /**
     * setter for property pubFilePermission
     * @param thePubFilePermission The pubFilePermission to set.
     */
    public final void setPubFilePermission(final PermissionType thePubFilePermission) {
        this.pubFilePermission = thePubFilePermission;
    }

    /**
     * Returns the standard base configuration.<br/>
     * @return the standard base configuration
     */
    public static BaseConfig getStandardBaseConfig() {
        final int standardFileLock = 10;
        final int standardChanges = 20;
        final BaseConfig cfg = new BaseConfig("./documents", standardFileLock, standardChanges, PermissionType.READER,
                PermissionType.READER, PermissionType.READER, PermissionType.NOTHING);
        return cfg;
    }
}
