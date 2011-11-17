/**
 * File: DBManager.java
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
package de.eod.jliki.db;

import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.eod.jliki.config.ConfigManager;

/**
 * Manages the database connection.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class DBManager {

    /** holds the logger. */
    // private final Logger logger = Logger.getLogger(DBManager.class);
    /** holds the database connection. */
    private SessionFactory hibSessionFactory = null;

    /**
     * Creates instance.<br/>
     * @param tableClasses the classes (with annotations) that can be stored in the database
     */
    public DBManager(final Class<?>[] tableClasses) {
        final String jdbcDriver = ConfigManager.getInstance().getConfig().getDbConfig().getDriver();
        final String dbUrl = ConfigManager.getInstance().getConfig().getDbConfig().getUrl();
        final String dbName = ConfigManager.getInstance().getConfig().getDbConfig().getDbName();
        final String dbUser = ConfigManager.getInstance().getConfig().getDbConfig().getUser();
        final String dbPass = ConfigManager.getInstance().getConfig().getDbConfig().getPassword();
        final Map<String, String> addParams = ConfigManager.getInstance().getConfig().getDbConfig()
                .getAdditionalParams();

        String connectUrl = dbUrl;
        if (!dbUrl.endsWith("/")) {
            connectUrl += "/";
        }
        connectUrl += dbName;

        final Properties dbProps = new Properties();
        dbProps.setProperty("hibernate.connection.driver_class", jdbcDriver);
        dbProps.setProperty("hibernate.connection.url", connectUrl);
        dbProps.setProperty("hibernate.connection.username", dbUser);
        dbProps.setProperty("hibernate.connection.password", dbPass);
        dbProps.setProperty("hibernate.c3p0.min_size", "5");
        dbProps.setProperty("hibernate.c3p0.max_size", "20");
        dbProps.setProperty("hibernate.c3p0.timeout", "1800");
        dbProps.setProperty("hibernate.c3p0.max_statements", "50");

        if (addParams != null) {
            for (final Map.Entry<String, String> entry : addParams.entrySet()) {
                dbProps.setProperty(entry.getKey(), entry.getValue());
            }
        }

        final Configuration hibConfig = new Configuration();
        hibConfig.setProperties(dbProps);

        for (final Class<?> clazz : tableClasses) {
            hibConfig.addAnnotatedClass(clazz);
        }

        this.hibSessionFactory = hibConfig.buildSessionFactory();
    }

    /**
     * Saves an object to the database.<br/>
     * @param obj the object to save
     */
    /*public final void saveObject(final Object obj) {
        final Session session = this.hibSessionFactory.openSession();
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
        session.close();
    }*/

    /**
     * Returns the database connection.<br/>
     * @return the connection
     */
    public final SessionFactory getSessionFactory() {
        return this.hibSessionFactory;
    }

}
