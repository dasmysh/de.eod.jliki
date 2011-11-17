/**
 * File: EMailConfig.java
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
 * 11.11.2011: File creation.
 */
package de.eod.jliki.config.jsfbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import de.eod.jliki.config.ConfigManager;

/**
 * This class holds the configuration of the used email server.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@ManagedBean(name = "emailConfigBean")
@ViewScoped
public class EMailConfigBean implements Serializable {

    /** holds serialization UID. */
    private static final long serialVersionUID = 1L;

    /** holds the logger. */
    private static final transient Logger LOGGER = Logger.getLogger(EMailConfigBean.class);

    /** holds the email server hostname. */
    private String hostname = "";
    /** holds the email server port. */
    private int port = 0;
    /** holds the email server username. */
    private String username = "";
    /** holds the email server password. */
    private String password = "";
    /** holds if the email server uses tls. */
    private boolean useTLS = false;
    /** holds the sender address. */
    private String senderAddress = "";

    /**
     * Class construction.<br/>
     */
    public EMailConfigBean() {
        this.refreshEMailConfig();
        EMailConfigBean.LOGGER.debug("Creating new emailConfigBean object!");
    }

    /**
     * getter for property hostname
     * @return returns the hostname.
    */
    public final String getHostname() {
        return this.hostname;
    }

    /**
     * setter for property hostname
     * @param theHostname The hostname to set.
     */
    public final void setHostname(final String theHostname) {
        this.hostname = theHostname;
    }

    /**
     * getter for property port
     * @return returns the port.
    */
    public final int getPort() {
        return this.port;
    }

    /**
     * setter for property port
     * @param thePort The port to set.
     */
    public final void setPort(final int thePort) {
        this.port = thePort;
    }

    /**
     * getter for property username
     * @return returns the username.
    */
    public final String getUsername() {
        return this.username;
    }

    /**
     * setter for property username
     * @param theUsername The username to set.
     */
    public final void setUsername(final String theUsername) {
        this.username = theUsername;
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
     * getter for property useTLS
     * @return returns the useTLS.
    */
    public final boolean isUseTLS() {
        return this.useTLS;
    }

    /**
     * setter for property useTLS
     * @param theUseTLS The useTLS to set.
     */
    public final void setUseTLS(final boolean theUseTLS) {
        this.useTLS = theUseTLS;
    }

    /**
     * getter for property senderAddress
     * @return returns the senderAddress.
    */
    public final String getSenderAddress() {
        return this.senderAddress;
    }

    /**
     * setter for property senderAddress
     * @param theSenderAddress The senderAddress to set.
     */
    public final void setSenderAddress(final String theSenderAddress) {
        this.senderAddress = theSenderAddress;
    }

    /**
     * Refreshes the email configuration bean with the global configuration.<br/>
     */
    public final void refreshEMailConfig() {
        this.hostname = ConfigManager.getInstance().getConfig().getEmailConfig().getHostname();
        this.port = ConfigManager.getInstance().getConfig().getEmailConfig().getPort();
        this.username = ConfigManager.getInstance().getConfig().getEmailConfig().getUsername();
        this.password = ConfigManager.getInstance().getConfig().getEmailConfig().getPassword();
        this.useTLS = ConfigManager.getInstance().getConfig().getEmailConfig().isUseTLS();
        this.senderAddress = ConfigManager.getInstance().getConfig().getEmailConfig().getSenderAddress();
    }

    /**
     * Saves the email configuration the the global configuration object and writes it to file.<br/>
     */
    public final void saveEMailConfig() {
        ConfigManager.getInstance().getConfig().getEmailConfig().setHostname(this.hostname);
        ConfigManager.getInstance().getConfig().getEmailConfig().setPort(this.port);
        ConfigManager.getInstance().getConfig().getEmailConfig().setUsername(this.username);
        ConfigManager.getInstance().getConfig().getEmailConfig().setPassword(this.password);
        ConfigManager.getInstance().getConfig().getEmailConfig().setUseTLS(this.useTLS);
        ConfigManager.getInstance().getConfig().getEmailConfig().setSenderAddress(this.senderAddress);
        ConfigManager.getInstance().saveConfig();
        EMailConfigBean.LOGGER.info("Wrote base configuration!");
    }
}
