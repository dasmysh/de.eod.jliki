/**
 * File: Messages.java
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
package de.eod.jliki.util;

import java.text.MessageFormat;
import java.util.Locale;
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

        final FacesMessage fm = Messages.createFacesMessage(severity, key, fc.getViewRoot().getLocale());
        fc.addMessage(clientId, fm);
        return fm.getDetail();
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

        final FacesMessage fm = Messages.createFacesMessage(severity, key, fc.getViewRoot().getLocale(), params);
        fc.addMessage(clientId, fm);
        return fm.getDetail();
    }

    /**
     * Creates a faces message from the message bundle.<br/>
     * @param severity the severity of the message
     * @param key the key of the string
     * @param locale the locale to use
     * @return the faces message containing the message to the key
     */
    public static FacesMessage createFacesMessage(final Severity severity, final String key, final Locale locale) {
        final ResourceBundle msgs = ResourceBundle.getBundle(Messages.MESSAGES_NAME, locale);
        String message = null;
        try {
            message = msgs.getString(key);
        } catch (final MissingResourceException e) {
            message = '@' + key + '@';
        }

        return new FacesMessage(severity, message, message);
    }

    /**
     * Creates a faces message from the message bundle and substitutes the parameters.<br/>
     * @param severity the severity of the message
     * @param key the key of the string
     * @param locale the locale to use
     * @param params the parameters to substitute
     * @return the faces message containing the message substituted to the key
     */
    public static FacesMessage createFacesMessage(final Severity severity, final String key, final Locale locale,
            final Object... params) {
        final ResourceBundle msgs = ResourceBundle.getBundle(Messages.MESSAGES_NAME, locale);
        String message = null;
        try {
            message = MessageFormat.format(msgs.getString(key), params);
        } catch (final MissingResourceException e) {
            message = '@' + key + '@';
        }

        return new FacesMessage(severity, message, message);
    }
}
