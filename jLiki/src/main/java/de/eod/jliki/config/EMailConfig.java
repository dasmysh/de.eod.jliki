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
package de.eod.jliki.config;

import java.io.Serializable;

/**
 * This class holds the configuration of the used email server.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class EMailConfig implements EMailConfigIfc, Serializable {

    /** holds serialization UID. */
    private static final long serialVersionUID = 1L;

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#
     * {@inheritDoc}
     */
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
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailHostname()
     * {@inheritDoc}
     */
    @Override
    public final String getEMailHostname() {
        return this.hostname;
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailHostname(String)
     * {@inheritDoc}
     */
    @Override
    public final void setEMailHostname(final String theHostname) {
        this.hostname = theHostname;
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailPort()
     * {@inheritDoc}
     */
    @Override
    public final int getEMailPort() {
        return this.port;
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailPort(int)
     * {@inheritDoc}
     */
    @Override
    public final void setEMailPort(final int thePort) {
        this.port = thePort;
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailUsername()
     * {@inheritDoc}
     */
    @Override
    public final String getEMailUsername() {
        return this.username;
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailUsername(String)
     * {@inheritDoc}
     */
    @Override
    public final void setEMailUsername(final String theUsername) {
        this.username = theUsername;
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailPassword()
     * {@inheritDoc}
     */
    @Override
    public final String getEMailPassword() {
        return this.password;
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailPassword(String)
     * {@inheritDoc}
     */
    @Override
    public final void setEMailPassword(final String thePassword) {
        this.password = thePassword;
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailUseTLS()
     * {@inheritDoc}
     */
    @Override
    public final boolean getEMailUseTLS() {
        return this.useTLS;
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailUseTLS(boolean)
     * {@inheritDoc}
     */
    @Override
    public final void setEMailUseTLS(final boolean theUseTLS) {
        this.useTLS = theUseTLS;
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#getEMailSenderAddress()
     * {@inheritDoc}
     */
    @Override
    public final String getEMailSenderAddress() {
        return this.senderAddress;
    }

    /**
     * @see de.eod.jliki.config.EMailConfigIfc#setEMailSenderAddress(String)
     * {@inheritDoc}
     */
    @Override
    public final void setEMailSenderAddress(final String theSenderAddress) {
        this.senderAddress = theSenderAddress;
    }

    /**
     * Generates a standard configuration for email server.<br/>
     * @return the new configuration
     */
    public static EMailConfig getStandardEMailConfig() {
        final EMailConfig cfg = new EMailConfig();
        cfg.setEMailHostname("hostname");
        cfg.setEMailPort(1);
        cfg.setEMailUsername("username");
        cfg.setEMailPassword("password");
        cfg.setEMailUseTLS(true);
        cfg.setEMailSenderAddress("email@address.com");
        return cfg;
    }
}
