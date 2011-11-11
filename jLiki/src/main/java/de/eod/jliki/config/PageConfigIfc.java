/**
 * File: PageConfigIfc.java
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

/**
 * Interface for the jLiki page configuration.<br/>
 * @author <a href="mailto:e@mail.de">Sebastian Maisch</a>
 *
 */
public interface PageConfigIfc {

    /**
     * Returns the pages keywords.<br/>
     * @return the keywords
     */
    String getPageKeywords();

    /**
     * Sets the pages keywords.<br/>
     * @param keywords the keywords
     */
    void setPageKeywords(final String keywords);

    /**
     * Returns the pages title.<br/>
     * @return the pages title
     */
    String getPageTitle();

    /**
     * Sets the pages title.<br/>
     * @param title the pages title
     */
    void setPageTitle(final String title);

    /**
     * Returns the pages name.<br/>
     * @return the pages name
     */
    String getPageName();

    /**
     * Sets the pages name.<br/>
     * @param name the pages name
     */
    void setPageName(final String name);

    /**
     * Returns the pages subtitle.<br/>
     * @return the pages subtitle
     */
    String getPageSubtitle();

    /**
     * Sets the pages subtitle.<br/>
     * @param subtitle the pages subtitle
     */
    void setPageSubtitle(final String subtitle);
}
