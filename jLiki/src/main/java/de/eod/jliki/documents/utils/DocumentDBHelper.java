/**
 * File: DocumentDBHelper.java
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
 * 20.11.2011: File creation.
 */
package de.eod.jliki.documents.utils;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import de.eod.jliki.db.servlets.DBSetup;
import de.eod.jliki.documents.dbbeans.Document;
import de.eod.jliki.documents.jsfbeans.DocumentRootBean;
import de.eod.jliki.users.dbbeans.Permission;
import de.eod.jliki.users.dbbeans.Permission.PermissionType;
import de.eod.jliki.users.jsfbeans.LoginBean;
import de.eod.jliki.users.utils.PermissionCategoryMap;
import de.eod.jliki.users.utils.UserDBHelper;

/**
 * Helper class for all document related database communication.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
public final class DocumentDBHelper {

    /**
     * Prevents class construction.<br/>
     */
    private DocumentDBHelper() {
    }

    /**
     * Adds a document to the database.<br/>
     * @param doc the document to add
     * @param login the login bean to associate the document to
     * @return true if the document was added successful
     */
    public static boolean addDocumentToDB(final Document doc, final LoginBean login) {
        final SessionFactory sf = DBSetup.getDbManager().getSessionFactory();
        final Session session = sf.openSession();
        final Transaction trx = session.beginTransaction();

        // find duplicates (in documents)
        final Criteria docQuery = session.createCriteria(Document.class).add(
                Restrictions.eq("shortname", doc.getShortname()));
        if (!docQuery.list().isEmpty()) {
            return false;
        }

        session.save(doc);

        // give the current user owner permission.
        final Permission newDocOwner = new Permission(doc.getShortname(), PermissionCategoryMap.CATEGORY_DOCROOT,
                PermissionType.OWNER);
        UserDBHelper.setHolderPermissions(login.getUserName(), new Permission[] {newDocOwner });
        login.setDocrootPermission(newDocOwner);

        trx.commit();
        session.close();

        return true;
    }

    /**
     * Gets the list of documents from the database and adds it to the docroot, checks the current logins permissions.<br/>
     * @param login the login object for permission check
     * @param docroot the docroot to add the document list to.
     */
    public static void getDocumentList(final LoginBean login, final DocumentRootBean docroot) {
        final SessionFactory sf = DBSetup.getDbManager().getSessionFactory();
        final Session session = sf.openSession();
        final Transaction trx = session.beginTransaction();

        Criteria docQuery = session.createCriteria(Document.class);
        final List<String> namesList = login.getDocrootPermissions().getAllWithType(PermissionType.READER);
        if (!"*".equals(namesList.get(0))) {
            docQuery = docQuery.add(Restrictions.in("shortname", namesList));
        }

        final List<?> docList = docQuery.list();
        final Iterator<?> it = docList.iterator();
        while (it.hasNext()) {
            final Document doc = (Document) it.next();
            docroot.getDocuments().add(
                    new Document(doc.getName(), doc.getShortname(), doc.getDescription(), doc.getLatexFile()));
        }

        trx.commit();
        session.close();
    }
}
