/**
 * File: CaptchaValidator.java
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
 * 12.11.2011: File creation.
 */
package de.eod.jliki.users.servlets;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import de.eod.jliki.util.Messages;

/**
 * Validator class for validating captchas.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class CaptchaValidator implements Validator {

    /**
     * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
     * {@inheritDoc}
     */
    @Override
    public final void validate(final FacesContext context, final UIComponent component, final Object value) {
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        final String sessionId = request.getSession().getId();
        final Locale sessionLocale = request.getLocale();
        final String val = StringUtils.strip((String) value);

        if (!CaptchaService.getInstance().validateResponseForID(sessionId, val)) {
            throw new ValidatorException(Messages.createFacesMessage(FacesMessage.SEVERITY_ERROR,
                    "user.register.captcha.mismatch", sessionLocale));
        }
    }
}
