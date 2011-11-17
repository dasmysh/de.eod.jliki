/**
 * File: LatexConfigBean.java
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
package de.eod.jliki.config.jsfbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import de.eod.jliki.config.ConfigManager;

/**
 * This class holds the jLiki server latex configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
@ManagedBean(name = "latexConfigBean")
@ViewScoped
public class LatexConfigBean implements Serializable {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** holds the logger. */
    private static final transient Logger LOGGER = Logger.getLogger(PageConfigBean.class);

    /**
     * Class construction.<br/>
     */
    public LatexConfigBean() {
        this.refreshLatexConfig();
    }

    /**
     * Refreshes the latex configuration bean with the global configuration.<br/>
     */
    public final void refreshLatexConfig() {
    }

    /**
     * Saves the latex configuration the the global configuration object and writes it to file.<br/>
     */
    public final void saveLatexConfig() {
        ConfigManager.getInstance().saveConfig();
        LatexConfigBean.LOGGER.info("Wrote base configuration!");
    }
}
