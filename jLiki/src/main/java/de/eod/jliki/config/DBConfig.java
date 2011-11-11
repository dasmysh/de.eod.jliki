/**
 * File: DBConfig.java
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

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBDriver()
     * {@inheritDoc}
     */
    @Override
    public final String getDBDriver() {
        return this.driver;
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBDriver(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public final void setDBDriver(final String theDriver) {
        this.driver = theDriver;
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBUrl()
     * {@inheritDoc}
     */
    @Override
    public final String getDBUrl() {
        return this.url;
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBUrl(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public final void setDBUrl(final String theUrl) {
        this.url = theUrl;
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBDatabaseName()
     * {@inheritDoc}
     */
    @Override
    public final String getDBDatabaseName() {
        return this.dbName;
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBDatabaseName(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public final void setDBDatabaseName(final String theDBName) {
        this.dbName = theDBName;
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBUsername()
     * {@inheritDoc}
     */
    @Override
    public final String getDBUsername() {
        return this.user;
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBUsername(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public final void setDBUsername(final String theUser) {
        this.user = theUser;
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBPassword()
     * {@inheritDoc}
     */
    @Override
    public final String getDBPassword() {
        return this.password;
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBPassword(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public final void setDBPassword(final String thePassword) {
        this.password = thePassword;
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBAdditionalParams()
     * {@inheritDoc}
     */
    @Override
    public final Map<String, String> getDBAdditionalParams() {
        return this.additionalParams;
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBAdditionalParams(java.util.List)
     * {@inheritDoc}
     */
    @Override
    public final void setDBAdditionalParams(
            final Map<String, String> theAdditionalParams) {
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

        /** @see http://docs.jboss.org/hibernate/core/3.6/javadocs/org/hibernate/dialect/package-frame.html */
        final Map<String, String> additionalParams = new HashMap<String, String>();
        additionalParams.put("hibernate.dialect",
                "org.hibernate.dialect.MySQLDialect");
        additionalParams.put("hibernate.hbm2ddl.auto", "update");
        cfg.setDBAdditionalParams(additionalParams);
        return cfg;
    }
}
