/**
 * File: ConfigManager.java
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

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Manages the jLiki configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public final class ConfigManager {

    /** holds the logger. */
    private final Logger logger = Logger.getLogger(ConfigManager.class);
    /** holds the ConfigManagers instance. */
    private static final ConfigManager INSTANCE = new ConfigManager();

    /** holds the configuration files filename. */
    private static final String CONFIG_FILENAME = "config/jlikiConfig.xml";

    /** holds the configuration. */
    private Configuration config = new Configuration();

    /**
     * Creates the class.<br/>
     */
    private ConfigManager() {
        if (!this.loadConfig()) {
            // create & save standard configuration if loading failed
            this.createStandardConfig();
            this.saveConfig();
        }
    }

    /**
     * Loads the configuration.<br/>
     * @return false if loading failed (config file not existent?)
     */
    private boolean loadConfig() {
        final File f = new File(ConfigManager.CONFIG_FILENAME);
        if (!f.exists()) {
            return false;
        }

        try {
            this.config = Configuration.loadFromFile(ConfigManager.CONFIG_FILENAME);
        } catch (final IOException e) {
            this.logger.error("Could not load config file: " + ConfigManager.CONFIG_FILENAME, e);
            return false;
        }

        this.logger.info("Config file loaded from: " + (new File(ConfigManager.CONFIG_FILENAME).getAbsolutePath()));
        return true;
    }

    /**
     * Creates the standard configuration.<br/>
     */
    private void createStandardConfig() {
        this.config = Configuration.getStandardConfig();
    }

    /**
     * Saves the configuration to a file.<br/>
     */
    public void saveConfig() {
        if (this.getConfig() == null) {
            this.logger.error("No configuration loaded, cannot save.");
            return;
        }

        try {
            this.getConfig().saveToFile(ConfigManager.CONFIG_FILENAME);
        } catch (final IOException e) {
            this.logger.error("Could not save config to file: " + ConfigManager.CONFIG_FILENAME, e);
            return;
        }
        this.logger.info("Config file created: " + (new File(ConfigManager.CONFIG_FILENAME).getAbsolutePath()));
    }

    /**
     * @return the instance
     */
    public static ConfigManager getInstance() {
        return ConfigManager.INSTANCE;
    }

    /**
     * getter for property config
     * @return returns the config.
    */
    public Configuration getConfig() {
        return this.config;
    }
}
