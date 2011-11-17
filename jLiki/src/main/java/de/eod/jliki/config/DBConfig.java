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
public class DBConfig implements Serializable {

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
     * Class construction, making sure there is only one instance.<br/>
     */
    public DBConfig() {
    }

    /**
     * Class construction (without instance management).<br/>
     * @param theDriver the database driver
     * @param theUrl the database url
     * @param theDBName the database name
     * @param theUser the dbs user
     * @param thePassword the users password
     * @param theAdditionalParams additional parameters
     */
    public DBConfig(final String theDriver, final String theUrl, final String theDBName, final String theUser,
            final String thePassword, final Map<String, String> theAdditionalParams) {
        this.driver = theDriver;
        this.url = theUrl;
        this.dbName = theDBName;
        this.user = theUser;
        this.password = thePassword;
        this.setAdditionalParams(theAdditionalParams);
    }

    /**
     * getter for property driver
     * @return returns the driver.
    */
    public final String getDriver() {
        return this.driver;
    }

    /**
     * setter for property driver
     * @param theDriver The driver to set.
     */
    public final void setDriver(final String theDriver) {
        this.driver = theDriver;
    }

    /**
     * getter for property url
     * @return returns the url.
    */
    public final String getUrl() {
        return this.url;
    }

    /**
     * setter for property url
     * @param theUrl The url to set.
     */
    public final void setUrl(final String theUrl) {
        this.url = theUrl;
    }

    /**
     * getter for property dbName
     * @return returns the dbName.
    */
    public final String getDbName() {
        return this.dbName;
    }

    /**
     * setter for property dbName
     * @param theDBName The dbName to set.
     */
    public final void setDbName(final String theDBName) {
        this.dbName = theDBName;
    }

    /**
     * getter for property user
     * @return returns the user.
    */
    public final String getUser() {
        return this.user;
    }

    /**
     * setter for property user
     * @param theUser The user to set.
     */
    public final void setUser(final String theUser) {
        this.user = theUser;
    }

    /**
     * getter for property password
     * @return returns the password.
    */
    public final String getPassword() {
        return this.password;
    }

    /**
     * setter for property password
     * @param thePassword The password to set.
     */
    public final void setPassword(final String thePassword) {
        this.password = thePassword;
    }

    /**
     * getter for property additionalParams
     * @return returns the additionalParams.
    */
    public final Map<String, String> getAdditionalParams() {
        return this.additionalParams;
    }

    /**
     * setter for property additionalParams
     * @param theAdditionalParams The additionalParams to set.
     */
    public final void setAdditionalParams(final Map<String, String> theAdditionalParams) {
        this.additionalParams = theAdditionalParams;
    }

    /**
     * Returns the standard page configuration.<br/>
     * @return the standard configuration
     */
    public static DBConfig getStandardDBConfig() {
        /** @see http://docs.jboss.org/hibernate/core/3.6/javadocs/org/hibernate/dialect/package-frame.html */
        final Map<String, String> additionalParams = new HashMap<String, String>();
        additionalParams.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        additionalParams.put("hibernate.hbm2ddl.auto", "update");

        final DBConfig cfg = new DBConfig("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306", "jLiki", "jLiki",
                "jLikiPassword", additionalParams);
        return cfg;
    }
}
