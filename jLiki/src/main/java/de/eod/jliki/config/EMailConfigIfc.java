/**
 * File: EMailConfigIfc.java
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

/**
 * Interface for the jLiki eMail configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
public interface EMailConfigIfc {

    /**
     * Returns the eMail-servers hostname.<br/>
     * @return the hostname
     */
    String getEMailHostname();

    /**
     * Sets the eMail-servers hostname.<br/>
     * @param theHostname the servers hostname
     */
    void setEMailHostname(final String theHostname);

    /**
     * Returns the eMail-servers port.<br/>
     * @return the port
     */
    int getEMailPort();

    /**
     * Sets the eMail-servers port.<br/>
     * @param thePort the servers port
     */
    void setEMailPort(final int thePort);

    /**
     * Returns the eMail-servers username.<br/>
     * @return the username
     */
    String getEMailUsername();

    /**
     * Sets the eMail-servers username.<br/>
     * @param theUsername the servers username
     */
    void setEMailUsername(final String theUsername);

    /**
     * Returns the eMail-servers users password.<br/>
     * @return the password
     */
    String getEMailPassword();

    /**
     * Sets the eMail-servers users password.<br/>
     * @param thePassword the users password
     */
    void setEMailPassword(final String thePassword);

    /**
     * Returns is the eMail-servers uses TLS.<br/>
     * @return true if the server uses TLS
     */
    boolean getEMailUseTLS();

    /**
     * Sets the usage of TLS of the eMail-server.<br/>
     * @param theUseTLS set to true if the server uses TLS
     */
    void setEMailUseTLS(final boolean theUseTLS);

    /**
     * Returns the eMail-servers users sender address.<br/>
     * @return the sender address
     */
    String getEMailSenderAddress();

    /**
     * Sets the eMail-servers users sender address.<br/>
     * @param theSenderAddress the sender address
     */
    void setEMailSenderAddress(final String theSenderAddress);
}
