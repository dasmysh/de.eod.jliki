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
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Manages the jLiki configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public final class ConfigManager implements PageConfigIfc, DBConfigIfc,
        EMailConfigIfc {

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
        this.config = Configuration.getStandardConfig();
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
        this.logger.info("Config file created: "
                + (new File(CONFIG_FILENAME).getAbsolutePath()));
    }

    /**
     * @return the instance
     */
    public static ConfigManager getInstance() {
        return INSTANCE;
    }

    /**
     * @see de.eod.jliki.config.ConfigurationIfc#getPageKeywords()
     * {@inheritDoc}
     */
    @Override
    public String getPageKeywords() {
        return this.config.getPageConfig().getPageKeywords();
    }

    /**
     * @see de.eod.jliki.config.ConfigurationIfc#setPageKeywords(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public void setPageKeywords(final String keywords) {
        this.config.getPageConfig().setPageKeywords(keywords);
    }

    /**
     * @see de.eod.jliki.config.ConfigurationIfc#getPageTitle()
     * {@inheritDoc}
     */
    @Override
    public String getPageTitle() {
        return this.config.getPageConfig().getPageTitle();
    }

    /**
     * @see de.eod.jliki.config.ConfigurationIfc#setPageTitle(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public void setPageTitle(final String title) {
        this.config.getPageConfig().setPageTitle(title);
    }

    /**
     * @see de.eod.jliki.config.ConfigurationIfc#getPageName()
     * {@inheritDoc}
     */
    @Override
    public String getPageName() {
        return this.config.getPageConfig().getPageName();
    }

    /**
     * @see de.eod.jliki.config.ConfigurationIfc#setPageName(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public void setPageName(final String name) {
        this.config.getPageConfig().setPageName(name);
    }

    /**
     * @see de.eod.jliki.config.ConfigurationIfc#getPageSubtitle()
     * {@inheritDoc}
     */
    @Override
    public String getPageSubtitle() {
        return this.config.getPageConfig().getPageSubtitle();
    }

    /**
     * @see de.eod.jliki.config.ConfigurationIfc#setPageSubtitle(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public void setPageSubtitle(final String subtitle) {
        this.config.getPageConfig().setPageSubtitle(subtitle);
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBDriver()
     * {@inheritDoc}
     */
    @Override
    public String getDBDriver() {
        return this.config.getDbConfig().getDBDriver();
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBDriver(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public void setDBDriver(final String driver) {
        this.config.getDbConfig().setDBDriver(driver);
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBUrl()
     * {@inheritDoc}
     */
    @Override
    public String getDBUrl() {
        return this.config.getDbConfig().getDBUrl();
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBUrl(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public void setDBUrl(final String url) {
        this.config.getDbConfig().setDBUrl(url);
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBDatabaseName()
     * {@inheritDoc}
     */
    @Override
    public String getDBDatabaseName() {
        return this.config.getDbConfig().getDBDatabaseName();
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBDatabaseName(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public void setDBDatabaseName(final String dbName) {
        this.config.getDbConfig().setDBDatabaseName(dbName);
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBUsername()
     * {@inheritDoc}
     */
    @Override
    public String getDBUsername() {
        return this.config.getDbConfig().getDBUsername();
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBUsername(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public void setDBUsername(final String user) {
        this.config.getDbConfig().setDBUsername(user);
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBPassword()
     * {@inheritDoc}
     */
    @Override
    public String getDBPassword() {
        return this.config.getDbConfig().getDBPassword();
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBPassword(java.lang.String)
     * {@inheritDoc}
     */
    @Override
    public void setDBPassword(final String password) {
        this.config.getDbConfig().setDBPassword(password);
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#getDBAdditionalParams()
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getDBAdditionalParams() {
        return this.config.getDbConfig().getDBAdditionalParams();
    }

    /**
     * @see de.eod.jliki.config.DBConfigIfc#setDBAdditionalParams(java.util.List)
     * {@inheritDoc}
     */
    @Override
    public void setDBAdditionalParams(final Map<String, String> additionalParams) {
        this.config.getDbConfig().setDBAdditionalParams(additionalParams);
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailHostname()
     * {@inheritDoc}
     */
    @Override
    public String getEMailHostname() {
        return this.config.getEmailConfig().getEMailHostname();
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailHostname(String)
     * {@inheritDoc}
     */
    @Override
    public void setEMailHostname(final String theHostname) {
        this.config.getEmailConfig().setEMailHostname(theHostname);
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailPort()
     * {@inheritDoc}
     */
    @Override
    public int getEMailPort() {
        return this.config.getEmailConfig().getEMailPort();
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailPort(int)
     * {@inheritDoc}
     */
    @Override
    public void setEMailPort(final int thePort) {
        this.config.getEmailConfig().setEMailPort(thePort);
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailUsername()
     * {@inheritDoc}
     */
    @Override
    public String getEMailUsername() {
        return this.config.getEmailConfig().getEMailUsername();
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailUsername(String)
     * {@inheritDoc}
     */
    @Override
    public void setEMailUsername(final String theUsername) {
        this.config.getEmailConfig().setEMailUsername(theUsername);
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailPassword()
     * {@inheritDoc}
     */
    @Override
    public String getEMailPassword() {
        return this.config.getEmailConfig().getEMailPassword();
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailPassword(String)
     * {@inheritDoc}
     */
    @Override
    public void setEMailPassword(final String thePassword) {
        this.config.getEmailConfig().setEMailPassword(thePassword);
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailUseTLS()
     * {@inheritDoc}
     */
    @Override
    public boolean getEMailUseTLS() {
        return this.config.getEmailConfig().getEMailUseTLS();
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailUseTLS(boolean)
     * {@inheritDoc}
     */
    @Override
    public void setEMailUseTLS(final boolean theUseTLS) {
        this.config.getEmailConfig().setEMailUseTLS(theUseTLS);
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailSenderAddress()
     * {@inheritDoc}
     */
    @Override
    public String getEMailSenderAddress() {
        return this.config.getEmailConfig().getEMailSenderAddress();
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailSenderAddress(String)
     * {@inheritDoc}
     */
    @Override
    public void setEMailSenderAddress(final String theSenderAddress) {
        this.config.getEmailConfig().setEMailSenderAddress(theSenderAddress);
    }
}
