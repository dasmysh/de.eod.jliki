/**
 * File: UserDBScheduler.java
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
 * 14.11.2011: File creation.
 */
package de.eod.jliki.users.utils;

import java.util.Calendar;
import java.util.Iterator;
import java.util.TimerTask;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import de.eod.jliki.db.servlets.DBSetup;
import de.eod.jliki.users.dbbeans.User;
import de.eod.jliki.users.dbbeans.User.ActiveState;

/**
 * Class doing some scheduling in the user related part of the database.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
public class UserDBScheduler extends TimerTask {

    /**
     * @see java.util.TimerTask#run()
     * {@inheritDoc}
     */
    @Override
    public final void run() {
        final SessionFactory sf = DBSetup.getDbManager().getSessionFactory();
        final Session session = sf.openSession();
        final Transaction trx = session.beginTransaction();

        // delete inactive users older than 24 hours
        final Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        final Query deleteInactive = session.createQuery("delete User as u where u.active=:active "
                + "and u.registerdate < :yesterday");
        deleteInactive.setInteger("active", ActiveState.INACTIVE.ordinal());
        deleteInactive.setDate("yesterday", yesterday.getTime());
        deleteInactive.executeUpdate();

        // delete session id if invalid
        final int aWeek = -7;
        final Calendar aWeekAgo = Calendar.getInstance();
        aWeekAgo.add(Calendar.DATE, aWeek);
        final Query deleteCookieId = session
                .createQuery("from User as u where u.cookieid is not empty and u.lastlogin < :aweekago");
        deleteCookieId.setDate("aweekago", aWeekAgo.getTime());

        final Iterator<?> it = deleteCookieId.iterate();
        while (it.hasNext()) {
            final User user = (User) it.next();
            user.setCookieid("");
            session.update(user);
        }

        trx.commit();
        session.close();
    }
}
