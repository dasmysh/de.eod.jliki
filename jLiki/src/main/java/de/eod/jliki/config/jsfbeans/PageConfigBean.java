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
package de.eod.jliki.config.jsfbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import de.eod.jliki.config.ConfigManager;

/**
 * This class holds the jLiki server page configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@ManagedBean(name = "pageConfigBean")
@ViewScoped
public class PageConfigBean implements Serializable {

    /** holds serialization UID. */
    private static final long serialVersionUID = 1L;

    /** holds the logger. */
    private static final transient Logger LOGGER = Logger.getLogger(PageConfigBean.class);

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
    public PageConfigBean() {
        this.refreshPageConfig();
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
     * Refreshes the page configuration bean with the global configuration.<br/>
     */
    public final void refreshPageConfig() {
        this.keywords = ConfigManager.getInstance().getConfig().getPageConfig().getKeywords();
        this.pageTitle = ConfigManager.getInstance().getConfig().getPageConfig().getPageTitle();
        this.pageName = ConfigManager.getInstance().getConfig().getPageConfig().getPageName();
        this.pageSubtitle = ConfigManager.getInstance().getConfig().getPageConfig().getPageSubtitle();
    }

    /**
     * Saves the page configuration the the global configuration object and writes it to file.<br/>
     */
    public final void savePageConfig() {
        ConfigManager.getInstance().getConfig().getPageConfig().setKeywords(this.keywords);
        ConfigManager.getInstance().getConfig().getPageConfig().setPageName(this.pageName);
        ConfigManager.getInstance().getConfig().getPageConfig().setPageSubtitle(this.pageSubtitle);
        ConfigManager.getInstance().getConfig().getPageConfig().setPageTitle(this.pageTitle);
        ConfigManager.getInstance().saveConfig();
        PageConfigBean.LOGGER.info("Wrote base configuration!");
    }
}
