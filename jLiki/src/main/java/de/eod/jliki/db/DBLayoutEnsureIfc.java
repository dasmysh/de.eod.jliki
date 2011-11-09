/**
 * Copyright (C) 2011 The jLiki Programming Team.
 *
 * This file is part of jLiki.
 *
 * jLiki is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Author: Sebastian Maisch
 * Last changes:
 * 07.11.2011: File creation.
 */
package de.eod.jliki.db;

import java.sql.Connection;

/**
 * Interface for classes that rely on a certain table layout in database.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public interface DBLayoutEnsureIfc {

    /**
     * Returns a unique name.<br/>
     * @return the name
     */
    String getUniqueName();

    /**
     * Checks if the database is up to date.<br/>
     * @param connection the connection to the database
     * @return true if database is up to date
     */
    boolean isDBUpToDate(final Connection connection);

    /**
     * Updates the database.<br/>
     * @param connection the connection to the database
     */
    void updateDB(final Connection connection);
}
