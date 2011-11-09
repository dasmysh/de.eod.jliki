/**
 * Copyright (C) ${year} The jLiki Programming Team.
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
 * 07.11.2011: File creation.
 */
package de.eod.jliki.config;

import java.util.Map;

/**
 * Interface for the jLiki database configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public interface DBConfigIfc {

    /**
     * Returns the db driver.<br/>
     * @return the db driver
     */
    String getDBDriver();

    /**
     * Sets the db driver.<br/>
     * @param driver the db driver
     */
    void setDBDriver(final String driver);

    /**
     * Returns the db url.<br/>
     * @return the db url
     */
    String getDBUrl();

    /**
     * Sets the db url.<br/>
     * @param url the db url
     */
    void setDBUrl(final String url);

    /**
     * Returns the database name.<br/>
     * @return the database name
     */
    String getDBDatabaseName();

    /**
     * Sets the database name.<br/>
     * @param dbName the database name
     */
    void setDBDatabaseName(final String dbName);

    /**
     * Returns the database username.<br/>
     * @return the database username
     */
    String getDBUsername();

    /**
     * Sets the database username.<br/>
     * @param user the database username
     */
    void setDBUsername(final String user);

    /**
     * Returns the database users password.<br/>
     * @return the database users password
     */
    String getDBPassword();

    /**
     * Sets the database users password.<br/>
     * @param password the database users password
     */
    void setDBPassword(final String password);

    /**
     * Returns a list of additional parameters for the database.<br/>
     * @return the list of additional parameters for the database
     */
    Map<String, String> getDBAdditionalParams();

    /**
     * Sets the list of additional parameters for the database.<br/>
     * @param additionalParams a list of additional parameters for the database
     */
    void setDBAdditionalParams(final Map<String, String> additionalParams);
}
