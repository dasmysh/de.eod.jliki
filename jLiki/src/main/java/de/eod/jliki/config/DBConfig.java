/**
 * Copyright (C) 2011 The jLiki Programming Team.
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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class holds the jLiki database configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class DBConfig implements DBConfigIfc, Serializable {

    /** holds serialization UID. */
    private static final long serialVersionUID = 1L;
    /** holds the database driver. */
    private String driver = "";
    /** holds the database url. */
    private String url = "";
    /** holds the database name. */
    private String dbName = "";
    /** holds the database username. */
    private String user = "";
    /** holds the database users password. */
    private String password = "";
    /** holds additional parameters for the database. */
    private Map<String, String> additionalParams = null;

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBDriver()
     */
    @Override
    public final String getDBDriver() {
        return this.driver;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBDriver(java.lang.String)
     */
    @Override
    public final void setDBDriver(final String theDriver) {
        this.driver = theDriver;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBUrl()
     */
    @Override
    public final String getDBUrl() {
        return this.url;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBUrl(java.lang.String)
     */
    @Override
    public final void setDBUrl(final String theUrl) {
        this.url = theUrl;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBDatabaseName()
     */
    @Override
    public final String getDBDatabaseName() {
        return this.dbName;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBDatabaseName(java.lang.String)
     */
    @Override
    public final void setDBDatabaseName(final String theDBName) {
        this.dbName = theDBName;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBUsername()
     */
    @Override
    public final String getDBUsername() {
        return this.user;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBUsername(java.lang.String)
     */
    @Override
    public final void setDBUsername(final String theUser) {
        this.user = theUser;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBPassword()
     */
    @Override
    public final String getDBPassword() {
        return this.password;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBPassword(java.lang.String)
     */
    @Override
    public final void setDBPassword(final String thePassword) {
        this.password = thePassword;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBAdditionalParams()
     */
    @Override
    public final Map<String, String> getDBAdditionalParams() {
        return this.additionalParams;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBAdditionalParams(java.util.List)
     */
    @Override
    public final void setDBAdditionalParams(final Map<String, String> theAdditionalParams) {
        this.additionalParams = theAdditionalParams;
    }

    /**
     * Returns the standard page configuration.<br/>
     * @return the standard configuration
     */
    public static DBConfig getStandardDBConfig() {
        final DBConfig cfg = new DBConfig();
        cfg.setDBDriver("com.mysql.jdbc.Driver");
        cfg.setDBUrl("jdbc:mysql://localhost:3306");
        cfg.setDBDatabaseName("jLiki");
        cfg.setDBUsername("jLiki");
        cfg.setDBPassword("jLikiPassword");
        cfg.setDBAdditionalParams(new HashMap<String, String>());
        return cfg;
    }
}
