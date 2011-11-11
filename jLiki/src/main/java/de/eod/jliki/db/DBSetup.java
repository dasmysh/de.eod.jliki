/**
 * File: DBSetup.java
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

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import de.eod.jliki.db.beans.User;

/**
 * Sets up the database for the jLiki.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class DBSetup extends GenericServlet {

    /** holds default serial id. */
    private static final long serialVersionUID = 1L;
    /** holds the logger. */
    private final transient Logger logger = Logger.getLogger(DBSetup.class);
    /** holds the DBManager instance. */
    private static DBManager dbManager = null;

    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public final void init() throws ServletException {
        super.init();

        this.logger.info("Starting with database setup...");

        final Class<?>[] tableClasses = new Class<?>[] {User.class};
        // setup database ...
        setDbManager(new DBManager(tableClasses));

        // setup initial database data
        this.ensureAdminUserInDB();

        this.logger.info("Finished with database setup...");
    }

    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    public void service(final ServletRequest arg0, final ServletResponse arg1) throws ServletException, IOException {
        // no nothing here ...
    }

    /**
     * @return the dbManager
     */
    public static final synchronized DBManager getDbManager() {
        return dbManager;
    }

    /**
     * @param theDBManager the dbManager to set
     */
    private static synchronized void setDbManager(final DBManager theDBManager) {
        DBSetup.dbManager = theDBManager;
    }

    /**
     * Tests if the admin account exists, if not it is set up.<br/>
     */
    private void ensureAdminUserInDB() {
        if (getDbManager() == null) {
            throw new IllegalStateException("Database Manager not initialized!");
        }

        final SessionFactory sf = getDbManager().getSessionFactory();
        final Session session = sf.openSession();
        session.beginTransaction();
        final Query query = session.createQuery("from User where username=:username");
        query.setString("username", "admin");
        final boolean adminInDB = query.list().size() == 0;
        session.getTransaction().commit();
        session.cancelQuery();
        session.close();
        if (adminInDB) {
            final User admin = new User("admin", "password", "", "", "");
            getDbManager().saveObject(admin);
        }
    }
}
