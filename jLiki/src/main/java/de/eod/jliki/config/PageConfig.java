/**
 * File: PageConfig.java
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

/**
 * This class holds the jLiki server page configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class PageConfig implements Serializable {

    /** holds serialization UID. */
    private static final long serialVersionUID = 1L;

    /** holds the pages keywords. */
    private String keywords = "";
    /** holds the pages title. */
    private String pageTitle = "";
    /** holds the pages name. */
    private String pageName = "";
    /** holds the pages subtitle. */
    private String pageSubtitle = "";

    /**
     * Class construction.<br/>
     */
    public PageConfig() {
    }

    /**
     * Class construction.<br/>
     * @param theKeywords the pages keywords
     * @param thePageTitle the pages title
     * @param thePageName the pages name
     * @param thePageSubtitle the pages subtitle
     */
    public PageConfig(final String theKeywords, final String thePageTitle, final String thePageName,
            final String thePageSubtitle) {
        this.keywords = theKeywords;
        this.pageTitle = thePageTitle;
        this.pageName = thePageName;
        this.pageSubtitle = thePageSubtitle;
    }

    /**
     * getter for property keywords
     * @return returns the keywords.
    */
    public final String getKeywords() {
        return this.keywords;
    }

    /**
     * setter for property keywords
     * @param theKeywords The keywords to set.
     */
    public final void setKeywords(final String theKeywords) {
        this.keywords = theKeywords;
    }

    /**
     * getter for property pageTitle
     * @return returns the pageTitle.
    */
    public final String getPageTitle() {
        return this.pageTitle;
    }

    /**
     * setter for property pageTitle
     * @param thePageTitle The pageTitle to set.
     */
    public final void setPageTitle(final String thePageTitle) {
        this.pageTitle = thePageTitle;
    }

    /**
     * getter for property pageName
     * @return returns the pageName.
    */
    public final String getPageName() {
        return this.pageName;
    }

    /**
     * setter for property pageName
     * @param thePageName The pageName to set.
     */
    public final void setPageName(final String thePageName) {
        this.pageName = thePageName;
    }

    /**
     * getter for property pageSubtitle
     * @return returns the pageSubtitle.
    */
    public final String getPageSubtitle() {
        return this.pageSubtitle;
    }

    /**
     * setter for property pageSubtitle
     * @param thePageSubtitle The pageSubtitle to set.
     */
    public final void setPageSubtitle(final String thePageSubtitle) {
        this.pageSubtitle = thePageSubtitle;
    }

    /**
     * Returns the standard page configuration.<br/>
     * @return the standard configuration
     */
    public static PageConfig getStandardPageConfig() {
        final PageConfig cfg = new PageConfig("jLiki,Latex", "jLiki - The LaTeX Wiki in Java", "Page title",
                "Page subtitle");
        return cfg;
    }
}
