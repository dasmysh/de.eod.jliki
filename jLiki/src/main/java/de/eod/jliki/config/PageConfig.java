/**
 * Copyright (C) ${year} The jLiki Programming Team.
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
 * This class holds the jLiki server page configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class PageConfig implements PageConfigIfc {

    /** holds the pages keywords. */
    private String keywords = "";
    /** holds the pages title. */
    private String pageTitle = "";
    /** holds the pages name. */
    private String pageName = "";
    /** holds the pages subtitle. */
    private String pageSubtitle = "";

    /* (non-Javadoc)
     * @see de.eod.jliki.config.PageConfigIfc#getPageKeywords()
     */
    @Override
    public final String getPageKeywords() {
        return this.keywords;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#setPageKeywords(java.lang.String)
     */
    @Override
    public final void setPageKeywords(final String theKeywords) {
        this.keywords = theKeywords;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#getPageTitle()
     */
    @Override
    public final String getPageTitle() {
        return this.pageTitle;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#setPageTitle(java.lang.String)
     */
    @Override
    public final void setPageTitle(final String title) {
        this.pageTitle = title;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#getPageName()
     */
    @Override
    public final String getPageName() {
        return this.pageName;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#setPageName(java.lang.String)
     */
    @Override
    public final void setPageName(final String name) {
        this.pageName = name;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#getPageSubtitle()
     */
    @Override
    public final String getPageSubtitle() {
        return this.pageSubtitle;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#setPageSubtitle(java.lang.String)
     */
    @Override
    public final void setPageSubtitle(final String subtitle) {
        this.pageSubtitle = subtitle;
    }

    /**
     * Returns the standard page configuration.<br/>
     * @return the standard configuration
     */
    public static PageConfig getStandardPageConfig() {
        final PageConfig cfg = new PageConfig();
        cfg.setPageKeywords("jLiki,Latex");
        cfg.setPageTitle("jLiki - The LaTeX Wiki in Java");
        cfg.setPageName("Page title");
        cfg.setPageSubtitle("Page subtitle");
        return cfg;
    }
}
