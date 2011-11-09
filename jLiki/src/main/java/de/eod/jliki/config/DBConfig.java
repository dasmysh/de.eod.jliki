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
package de.eod.jliki.config;

import java.util.Map;

/**
 * This class holds the jLiki database configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class DBConfig implements DBConfigIfc {

    /** holds the database driver. */
    private String driver = "com.mysql.jdbc.Driver";
    /** holds the database url. */
    private String url = "jdbc:mysql://localhost:3306";
    /** holds the database name. */
    private String dbName = "jLiki";
    /** holds the database username. */
    private String user = "jLiki";
    /** holds the database users password. */
    private String password = "jLikiPassword";
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

}
