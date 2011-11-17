/**
 * File: DBConfig.java
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import de.eod.jliki.config.ConfigManager;

/**
 * This class holds the jLiki database configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@ManagedBean(name = "dbConfigBean")
@ViewScoped
public class DBConfigBean implements Serializable {

    /** holds serialization UID. */
    private static final long serialVersionUID = 1L;

    /** holds the logger. */
    private static final transient Logger LOGGER = Logger.getLogger(DBConfigBean.class);

    /** holds the database driver. */
    private String driver = "";
    /** holds the database url. */
    private String url = "";
    /** holds the database name. */
    private String dbName = "";
    /** holds the database username. */
    private String user = "";
    /** holds the database users password. */
    private String password = "";
    /** holds additional parameters for the database. */
    private Map<String, ParamMapValue> additionalParams = null;

    /**
     * Class construction, making sure there is only one instance.<br/>
     */
    public DBConfigBean() {
        this.refreshDBConfig();
    }

    /**
     * getter for property driver
     * @return returns the driver.
    */
    public final String getDriver() {
        return this.driver;
    }

    /**
     * setter for property driver
     * @param theDriver The driver to set.
     */
    public final void setDriver(final String theDriver) {
        this.driver = theDriver;
    }

    /**
     * getter for property url
     * @return returns the url.
    */
    public final String getUrl() {
        return this.url;
    }

    /**
     * setter for property url
     * @param theUrl The url to set.
     */
    public final void setUrl(final String theUrl) {
        this.url = theUrl;
    }

    /**
     * getter for property dbName
     * @return returns the dbName.
    */
    public final String getDbName() {
        return this.dbName;
    }

    /**
     * setter for property dbName
     * @param theDBName The dbName to set.
     */
    public final void setDbName(final String theDBName) {
        this.dbName = theDBName;
    }

    /**
     * getter for property user
     * @return returns the user.
    */
    public final String getUser() {
        return this.user;
    }

    /**
     * setter for property user
     * @param theUser The user to set.
     */
    public final void setUser(final String theUser) {
        this.user = theUser;
    }

    /**
     * getter for property password
     * @return returns the password.
    */
    public final String getPassword() {
        return this.password;
    }

    /**
     * setter for property password
     * @param thePassword The password to set.
     */
    public final void setPassword(final String thePassword) {
        this.password = thePassword;
    }

    /**
     * getter for property additionalParams
     * @return returns the additionalParams.
    */
    public final Map<String, ParamMapValue> getAdditionalParams() {
        return this.additionalParams;
    }

    /**
     * setter for property additionalParams
     * @param theAdditionalParams The additionalParams to set.
     */
    public final void setAdditionalParams(final Map<String, ParamMapValue> theAdditionalParams) {
        this.additionalParams = theAdditionalParams;
    }

    /**
     * Returns the additional parameters as a list.<br/>
     * @return the list of parameters
     */
    public final List<Map.Entry<String, ParamMapValue>> getAdditionalParamsList() {
        return new ArrayList<Map.Entry<String, ParamMapValue>>(this.additionalParams.entrySet());
    }

    /**
     * Sets the additional parameters from a <code>Map&lt;String, String&gt;</code>.<br/>
     * @param theAdditionalParams the map to set
     */
    private void setAdditionalParamsInternal(final Map<String, String> theAdditionalParams) {
        this.additionalParams = new HashMap<String, ParamMapValue>();
        for (final Map.Entry<String, String> entry : theAdditionalParams.entrySet()) {
            this.additionalParams.put(entry.getKey(), new ParamMapValue(entry.getValue()));
        }
    }

    /**
     * Returns the additional parameters as a <code>Map&lt;String, String&gt;</code>.<br/>
     * @return the additional parameters
     */
    private Map<String, String> getAdditinalParamsInternal() {
        final Map<String, String> res = new HashMap<String, String>();
        for (final Map.Entry<String, ParamMapValue> entry : this.additionalParams.entrySet()) {
            res.put(entry.getKey(), entry.getValue().getValue());
        }
        return res;
    }

    /**
     * Sets the editable flag of a given parameter.<br/>
     * @param entry the parameter map entry to change
     */
    public final void editAddParamEntryAction(final Map.Entry<String, ParamMapValue> entry) {
        this.additionalParams.get(entry.getKey()).setEditable(true);
    }

    /**
     * Saves the changes made to a parameter value.<br/>
     * @param entry the parameter to save
     */
    public final void saveAddParamEntryAction(final Map.Entry<String, ParamMapValue> entry) {
        if (entry.getKey().startsWith("@") || entry.getValue().getValue().startsWith("@")) {
            this.deleteAddParamEntryAction(entry);
            return;
        }

        this.additionalParams.get(entry.getKey()).setValue(entry.getValue().getValue());
        this.additionalParams.get(entry.getKey()).setEditable(false);
        this.additionalParams.get(entry.getKey()).setNewCreated(false);
    }

    /**
     * Deletes a given parameter from the map.<br/>
     * @param entry the parameter to delete
     */
    public final void deleteAddParamEntryAction(final Map.Entry<String, ParamMapValue> entry) {
        this.additionalParams.remove(entry.getKey());
    }

    /**
     * Creates a new parameter and sets it editable.<br/>
     */
    public final void createAddParamEntryAction() {
        this.additionalParams.put("@key", new ParamMapValue());
    }

    /**
     * Refreshes the db configuration bean with the global configuration.<br/>
     */
    public final void refreshDBConfig() {
        this.driver = ConfigManager.getInstance().getConfig().getDbConfig().getDriver();
        this.url = ConfigManager.getInstance().getConfig().getDbConfig().getUrl();
        this.dbName = ConfigManager.getInstance().getConfig().getDbConfig().getDbName();
        this.user = ConfigManager.getInstance().getConfig().getDbConfig().getUser();
        this.password = ConfigManager.getInstance().getConfig().getDbConfig().getPassword();
        this.setAdditionalParamsInternal(ConfigManager.getInstance().getConfig().getDbConfig().getAdditionalParams());
    }

    /**
     * Saves the db configuration the the global configuration object and writes it to file.<br/>
     */
    public final void saveDBConfig() {
        ConfigManager.getInstance().getConfig().getDbConfig().setDriver(this.driver);
        ConfigManager.getInstance().getConfig().getDbConfig().setUrl(this.url);
        ConfigManager.getInstance().getConfig().getDbConfig().setDbName(this.dbName);
        ConfigManager.getInstance().getConfig().getDbConfig().setUser(this.user);
        ConfigManager.getInstance().getConfig().getDbConfig().setPassword(this.password);
        ConfigManager.getInstance().getConfig().getDbConfig().setAdditionalParams(this.getAdditinalParamsInternal());
        ConfigManager.getInstance().saveConfig();
        DBConfigBean.LOGGER.info("Wrote base configuration!");
    }

    /**
     * Helper class for viewing configuration of additional parameters.<br/>
     * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
     */
    public static class ParamMapValue implements Serializable {

        /** holds serialVersionUID. */
        private static final long serialVersionUID = 1L;

        /** holds the value. */
        private String value;
        /** holds if the entry is editable. */
        private boolean editable;
        /** holds if the entry was newly created by the user. */
        private boolean newCreated = false;

        /**
         * Class construction.<br/>
         * @param theValue the value to set
         */
        public ParamMapValue(final String theValue) {
            this.value = theValue;
            this.editable = false;
        }

        /**
         * Class construction.<br/>
         */
        public ParamMapValue() {
            this.value = "@value";
            this.editable = true;
            this.newCreated = true;
        }

        /**
         * getter for property value
         * @return returns the value.
        */
        public final String getValue() {
            return this.value;
        }

        /**
         * setter for property value
         * @param theValue The value to set.
         */
        public final void setValue(final String theValue) {
            this.value = theValue;
        }

        /**
         * getter for property editable
         * @return returns the editable.
        */
        public final boolean isEditable() {
            return this.editable;
        }

        /**
         * setter for property editable
         * @param theEditable The editable to set.
         */
        public final void setEditable(final boolean theEditable) {
            this.editable = theEditable;
        }

        /**
         * getter for property newCreated
         * @return returns the newCreated.
        */
        public final boolean isNewCreated() {
            return this.newCreated;
        }

        /**
         * setter for property newCreated
         * @param theNewCreated The newCreated to set.
         */
        public final void setNewCreated(final boolean theNewCreated) {
            this.newCreated = theNewCreated;
        }
    }
}
