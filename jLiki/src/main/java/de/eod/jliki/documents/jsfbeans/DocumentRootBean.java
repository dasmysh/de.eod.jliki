/**
 * File: DocumentRoot.java
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
 * 17.11.2011: File creation.
 */
package de.eod.jliki.documents.jsfbeans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import de.eod.jliki.documents.dbbeans.Document;
import de.eod.jliki.documents.utils.DocumentDBHelper;
import de.eod.jliki.users.dbbeans.Permission.PermissionType;
import de.eod.jliki.users.jsfbeans.LoginBean;

/**
 * Enables the facelets access to all documents.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
@ManagedBean(name = "docrootBean")
@ViewScoped
public class DocumentRootBean implements Serializable {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** holds all documents. */
    private final List<Document> documents;

    /** holds the name of a new document. */
    private String createDocName;
    /** holds the shortname of a new document. */
    private String createDocShortname;
    /** holds the main filename of a new document. */
    private String createDocMainfilename;
    /** holds the description of a new document. */
    private String createDocDescription;
    /** holds the all user permission of a new document. */
    private PermissionType createDocUserPermission;
    /** holds the group permission of a new document. */
    private PermissionType createDocGroupPermission;

    /**
     * Class construction.<br/>
     */
    public DocumentRootBean() {
        this.documents = new LinkedList<Document>();
        this.refreshDocroot();
    }

    /**
     * getter for property documents
     * @return returns the documents.
    */
    public final List<Document> getDocuments() {
        return this.documents;
    }

    /**
     * getter for property createDocName
     * @return returns the createDocName.
    */
    public final String getCreateDocName() {
        return this.createDocName;
    }

    /**
     * setter for property createDocName
     * @param theCreateDocName The createDocName to set.
     */
    public final void setCreateDocName(final String theCreateDocName) {
        this.createDocName = theCreateDocName;
    }

    /**
     * getter for property createDocShortname
     * @return returns the createDocShortname.
    */
    public final String getCreateDocShortname() {
        return this.createDocShortname;
    }

    /**
     * setter for property createDocShortname
     * @param theCreateDocShortname The createDocShortname to set.
     */
    public final void setCreateDocShortname(final String theCreateDocShortname) {
        this.createDocShortname = theCreateDocShortname;
    }

    /**
     * getter for property createDocMainfilename
     * @return returns the createDocMainfilename.
    */
    public final String getCreateDocMainfilename() {
        return this.createDocMainfilename;
    }

    /**
     * setter for property createDocMainfilename
     * @param theCreateDocMainfilename The createDocMainfilename to set.
     */
    public final void setCreateDocMainfilename(final String theCreateDocMainfilename) {
        this.createDocMainfilename = theCreateDocMainfilename;
    }

    /**
     * getter for property createDocDescription
     * @return returns the createDocDescription.
    */
    public final String getCreateDocDescription() {
        return this.createDocDescription;
    }

    /**
     * setter for property createDocDescription
     * @param theCreateDocDescription The createDocDescription to set.
     */
    public final void setCreateDocDescription(final String theCreateDocDescription) {
        this.createDocDescription = theCreateDocDescription;
    }

    /**
     * getter for property createDocUserPermission
     * @return returns the createDocUserPermission.
    */
    public final PermissionType getCreateDocUserPermission() {
        return this.createDocUserPermission;
    }

    /**
     * setter for property createDocUserPermission
     * @param theCreateDocUserPermission The createDocUserPermission to set.
     */
    public final void setCreateDocUserPermission(final PermissionType theCreateDocUserPermission) {
        this.createDocUserPermission = theCreateDocUserPermission;
    }

    /**
     * getter for property createDocGroupPermission
     * @return returns the createDocGroupPermission.
    */
    public final PermissionType getCreateDocGroupPermission() {
        return this.createDocGroupPermission;
    }

    /**
     * setter for property createDocGroupPermission
     * @param theCreateDocGroupPermission The createDocGroupPermission to set.
     */
    public final void setCreateDocGroupPermission(final PermissionType theCreateDocGroupPermission) {
        this.createDocGroupPermission = theCreateDocGroupPermission;
    }

    /**
     * Creates a new document.<br/>
     */
    public final void createNewDocument() {
        final Document doc = new Document(this.createDocName, this.createDocShortname, this.createDocDescription,
                this.createDocMainfilename);
        final FacesContext context = FacesContext.getCurrentInstance();
        final LoginBean login = context.getApplication()
                .evaluateExpressionGet(context, "#{loginBean}", LoginBean.class);
        if (DocumentDBHelper.addDocumentToDB(doc, login)) {
            this.refreshDocroot();
        }

        this.documents.add(doc);
    }

    /**
     * Clears the new document entries, refreshes the document list.<br/>
     */
    public final void refreshDocroot() {
        this.createDocName = "";
        this.createDocShortname = "";
        this.createDocMainfilename = "";
        this.createDocDescription = "";
        this.createDocUserPermission = PermissionType.NOTHING;
        this.createDocGroupPermission = PermissionType.NOTHING;

        this.documents.clear();
        final FacesContext context = FacesContext.getCurrentInstance();
        final LoginBean login = context.getApplication()
                .evaluateExpressionGet(context, "#{loginBean}", LoginBean.class);
        DocumentDBHelper.getDocumentList(login, this);
    }
}
