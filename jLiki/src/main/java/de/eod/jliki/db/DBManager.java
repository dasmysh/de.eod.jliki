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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.eod.jliki.config.ConfigManager;

/**
 * Manages the database connection.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class DBManager {

    /** holds the logger. */
    private final Logger logger = Logger.getLogger(DBManager.class);
    /** holds the database connection. */
    private Connection connection = null;
    /** holds a set of layout ensurer objects. */
    private final Map<String, DBLayoutEnsureIfc> ensurers = new HashMap<String, DBLayoutEnsureIfc>();

    /**
     * Creates instance.<br/>
     */
    public DBManager() {
        final String jdbcDriver = ConfigManager.getInstance().getDBDriver();
        final String dbUrl = ConfigManager.getInstance().getDBUrl();
        final String dbName = ConfigManager.getInstance().getDBDatabaseName();
        final String dbUser = ConfigManager.getInstance().getDBUsername();
        final String dbPass = ConfigManager.getInstance().getDBPassword();
        final Map<String, String> addParams = ConfigManager.getInstance().getDBAdditionalParams();

        final Properties dbProps = new Properties();
        dbProps.setProperty("user", dbUser);
        dbProps.setProperty("password", dbPass);
        if (addParams != null) {
            for (final Map.Entry<String, String> entry : addParams.entrySet()) {
                dbProps.setProperty(entry.getKey(), entry.getValue());
            }
        }

        String connectUrl = dbUrl;
        if (!dbUrl.endsWith("/")) {
            connectUrl += "/";
        }
        connectUrl += dbName;

        try {
            Class.forName(jdbcDriver).newInstance();
        } catch (final InstantiationException e) {
            this.logger.fatal("Could not find jdbc driver: " + jdbcDriver, e);
            return;
        } catch (final IllegalAccessException e) {
            this.logger.fatal("Could not find jdbc driver: " + jdbcDriver, e);
            return;
        } catch (final ClassNotFoundException e) {
            this.logger.fatal("Could not find jdbc driver: " + jdbcDriver, e);
            return;
        }

        try {
            this.connection = DriverManager.getConnection(connectUrl, dbProps);
        } catch (final SQLException e) {
            this.logger.error("Could not connect to database: " + connectUrl, e);
            return;
        }
    }

    /**
     * Adds a layout ensurer to the list.<br/>
     * @param ens the ensurer
     */
    public final void addLayoutEnsurer(final DBLayoutEnsureIfc ens) {
        this.ensurers.put(ens.getUniqueName(), ens);
    }

    /**
     * Ensures the database is up to date.<br/>
     */
    public final void ensureCurrentDB() {
        for (final DBLayoutEnsureIfc ens : this.ensurers.values()) {
            if (!ens.isDBUpToDate(this.connection)) {
                this.logger.warn("Database is not up to date for " + ens.getUniqueName() + ", updating...");
                ens.updateDB(this.connection);
            }
        }
    }
}
