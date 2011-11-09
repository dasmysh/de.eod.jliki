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

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Manages the jLiki configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public final class ConfigManager implements PageConfigIfc, DBConfigIfc {

    /** holds the logger. */
    private final Logger logger = Logger.getLogger(ConfigManager.class);
    /** holds the ConfigManagers instance. */
    private static final ConfigManager INSTANCE = new ConfigManager();

    /** holds the configuration files filename. */
    private static final String CONFIG_FILENAME = "config/jlikiConfig.xml";

    /** holds the configuration. */
    private Configuration config = null;

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
        final File f = new File(CONFIG_FILENAME);
        if (!f.exists()) {
            return false;
        }

        try {
            this.config = Configuration.loadFromFile(CONFIG_FILENAME);
        } catch (final IOException e) {
            this.logger.error("Could not load config file: " + CONFIG_FILENAME, e);
            return false;
        }

        this.logger.info("Config file loaded from: " + (new File(CONFIG_FILENAME).getAbsolutePath()));
        return true;
    }

    /**
     * Creates the standard configuration.<br/>
     */
    private void createStandardConfig() {
        this.config = new Configuration();
    }

    /**
     * Saves the configuration to a file.<br/>
     */
    public void saveConfig() {
        if (this.config == null) {
            this.logger.error("No configuration loaded, cannot save.");
            return;
        }

        try {
            this.config.saveToFile(CONFIG_FILENAME);
        } catch (final IOException e) {
            this.logger.error("Could not save config to file: " + CONFIG_FILENAME, e);
            return;
        }
        this.logger.info("Config file created: " + (new File(CONFIG_FILENAME).getAbsolutePath()));
    }

    /**
     * @return the instance
     */
    public static ConfigManager getInstance() {
        return INSTANCE;
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#getPageKeywords()
     */
    @Override
    public String getPageKeywords() {
        return this.config.getPageConfig().getPageKeywords();
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#setPageKeywords(java.lang.String)
     */
    @Override
    public void setPageKeywords(final String keywords) {
        this.config.getPageConfig().setPageKeywords(keywords);
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#getPageTitle()
     */
    @Override
    public String getPageTitle() {
        return this.config.getPageConfig().getPageTitle();
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#setPageTitle(java.lang.String)
     */
    @Override
    public void setPageTitle(final String title) {
        this.config.getPageConfig().setPageTitle(title);
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#getPageName()
     */
    @Override
    public String getPageName() {
        return this.config.getPageConfig().getPageName();
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#setPageName(java.lang.String)
     */
    @Override
    public void setPageName(final String name) {
        this.config.getPageConfig().setPageName(name);
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#getPageSubtitle()
     */
    @Override
    public String getPageSubtitle() {
        return this.config.getPageConfig().getPageSubtitle();
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.ConfigurationIfc#setPageSubtitle(java.lang.String)
     */
    @Override
    public void setPageSubtitle(final String subtitle) {
        this.config.getPageConfig().setPageSubtitle(subtitle);
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBDriver()
     */
    @Override
    public String getDBDriver() {
        return this.config.getDbConfig().getDBDriver();
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBDriver(java.lang.String)
     */
    @Override
    public void setDBDriver(final String driver) {
        this.config.getDbConfig().setDBDriver(driver);
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBUrl()
     */
    @Override
    public String getDBUrl() {
        return this.config.getDbConfig().getDBUrl();
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBUrl(java.lang.String)
     */
    @Override
    public void setDBUrl(final String url) {
        this.config.getDbConfig().setDBUrl(url);
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBDatabaseName()
     */
    @Override
    public String getDBDatabaseName() {
        return this.config.getDbConfig().getDBDatabaseName();
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBDatabaseName(java.lang.String)
     */
    @Override
    public void setDBDatabaseName(final String dbName) {
        this.config.getDbConfig().setDBDatabaseName(dbName);
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBUsername()
     */
    @Override
    public String getDBUsername() {
        return this.config.getDbConfig().getDBUsername();
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBUsername(java.lang.String)
     */
    @Override
    public void setDBUsername(final String user) {
        this.config.getDbConfig().setDBUsername(user);
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBPassword()
     */
    @Override
    public String getDBPassword() {
        return this.config.getDbConfig().getDBPassword();
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBPassword(java.lang.String)
     */
    @Override
    public void setDBPassword(final String password) {
        this.config.getDbConfig().setDBPassword(password);
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#getDBAdditionalParams()
     */
    @Override
    public Map<String, String> getDBAdditionalParams() {
        return this.config.getDbConfig().getDBAdditionalParams();
    }

    /* (non-Javadoc)
     * @see de.eod.jliki.config.DBConfigIfc#setDBAdditionalParams(java.util.List)
     */
    @Override
    public void setDBAdditionalParams(final Map<String, String> additionalParams) {
        this.config.getDbConfig().setDBAdditionalParams(additionalParams);
    }
}
