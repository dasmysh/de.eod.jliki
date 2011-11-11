/**
 * File: ConfigBean.java
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
 * 06.11.2011: File creation.
 */
package de.eod.jliki.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.eod.jliki.config.ConfigManager;

/**
 * This class represents the liki configuration.<br/>
 *
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
@ManagedBean(name = "configBean")
@SessionScoped
public class ConfigBean implements Serializable {

    /** holds serialization UID. */
    private static final long serialVersionUID = 1L;

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
