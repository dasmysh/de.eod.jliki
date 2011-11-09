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
 * 06.11.2011: File creation.
 */
package de.eod.jliki.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.eod.jliki.config.ConfigManager;

/**
 * This class represents the liki configuration.<br/>
 *
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
@ManagedBean
@SessionScoped
public class ConfigBean {

    /**
     * @return the keywords
     */
    public final String getKeywords() {
        return ConfigManager.getInstance().getPageKeywords();
    }

    /**
     * @return the pageTitle
     */
    public final String getPageTitle() {
        return ConfigManager.getInstance().getPageTitle();
    }

    /**
     * @return the pageName
     */
    public final String getPageName() {
        return ConfigManager.getInstance().getPageName();
    }

    /**
     * @return the subtitle
     */
    public final String getPageSubtitle() {
        return ConfigManager.getInstance().getPageSubtitle();
    }
}
