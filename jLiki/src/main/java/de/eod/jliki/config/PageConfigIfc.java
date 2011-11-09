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
