/**
 * Copyright (C) 2011 The jLiki Programming Team.
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
package de.eod.jliki.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Helper class for bundled messages with parameters.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public final class Messages {

    /** holds the name of the resource bundle. */
    private static final String MESSAGES_NAME = "de.eod.jliki.Messages";

    /** prevents construction.<br/> */
    private Messages() {
    }

    /**
     * Adds a message from the message bundle to faces and returns its content<br/>
     * @param clientId the receiver of the message
     * @param severity the severity of the message
     * @param key the key of the string
     * @return the string from the message bundle
     */
    public static String addFacesMessage(final String clientId, final Severity severity, final String key) {
        final FacesContext fc = FacesContext.getCurrentInstance();
        final ResourceBundle msgs = ResourceBundle.getBundle(MESSAGES_NAME, fc.getViewRoot().getLocale());

        try {
            final String message = msgs.getString(key);
            final FacesMessage fm = new FacesMessage(severity, message, message);
            fc.addMessage(clientId, fm);
            return message;

        } catch (final MissingResourceException e) {
            return '@' + key + '@';
        }
    }

    /**
     * Adds a parameterized message from the message bundle to faces and returns its content.<br/>
     * @param clientId the receiver of the message
     * @param severity the severity of the message
     * @param key the key of the string
     * @param params the parameters
     * @return the final string
     */
    public static String addFacesMessage(final String clientId, final Severity severity, final String key,
            final Object... params) {
        final FacesContext fc = FacesContext.getCurrentInstance();
        final ResourceBundle msgs = ResourceBundle.getBundle(MESSAGES_NAME, fc.getViewRoot().getLocale());

        try {
            final String message = MessageFormat.format(msgs.getString(key), params);
            final FacesMessage fm = new FacesMessage(severity, message, message);
            fc.addMessage(clientId, fm);
            return message;
        } catch (final MissingResourceException e) {
            return '@' + key + '@';
        }
    }
}
