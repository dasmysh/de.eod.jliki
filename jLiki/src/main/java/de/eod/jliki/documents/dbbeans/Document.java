/**
 * File: Document.java
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
package de.eod.jliki.documents.dbbeans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * This represents a document in the docroot.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
@Entity
public class Document implements Serializable {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** holds the documents id. */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;
    /** holds the documents name. */
    private String name;
    /** holds the description. */
    @Type(type = "text")
    private String description;
    /** holds the main latex filename. */
    private String latexFile;
    /** holds the documents short name. */
    private String shortname;

    /**
     * Class construction.<br/>
     */
    public Document() {
        this.id = 0;
        this.name = "";
    }

    /**
     * Class construction.<br/>
     * @param theName the documents name
     * @param theShortname the documents short name
     * @param desc the documents description
     * @param theFilename the main filename of the document
     */
    public Document(final String theName, final String theShortname, final String desc, final String theFilename) {
        this.id = 0;
        this.name = theName;
        this.shortname = theShortname;
        this.description = desc;
        this.latexFile = theFilename;
    }

    /**
     * getter for property id
     * @return returns the id.
    */
    public final Integer getId() {
        return this.id;
    }

    /**
     * setter for property id
     * @param theId The id to set.
     */
    public final void setId(final Integer theId) {
        this.id = theId;
    }

    /**
     * getter for property name
     * @return returns the name.
    */
    public final String getName() {
        return this.name;
    }

    /**
     * setter for property name
     * @param theName The name to set.
     */
    public final void setName(final String theName) {
        this.name = theName;
    }

    /**
     * getter for property description
     * @return returns the description.
    */
    public final String getDescription() {
        return this.description;
    }

    /**
     * setter for property description
     * @param theDescription The description to set.
     */
    public final void setDescription(final String theDescription) {
        this.description = theDescription;
    }

    /**
     * getter for property latexFile
     * @return returns the latexFile.
    */
    public final String getLatexFile() {
        return this.latexFile;
    }

    /**
     * setter for property latexFile
     * @param theLatexFile The latexFile to set.
     */
    public final void setLatexFile(final String theLatexFile) {
        this.latexFile = theLatexFile;
    }

    /**
     * getter for property shortname
     * @return returns the shortname.
    */
    public final String getShortname() {
        return this.shortname;
    }

    /**
     * setter for property shortname
     * @param theShortname The shortname to set.
     */
    public final void setShortname(final String theShortname) {
        this.shortname = theShortname;
    }
}
