/**
 * File: CaptchaServlet.java
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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet class for generating captchas.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class CaptchaServlet extends HttpServlet {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;
    /** holds the logger. */
    private static final Logger LOGGER = Logger.getLogger(CaptchaServlet.class);

    /**
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     * {@inheritDoc}
     */
    @Override
    public final void init(final ServletConfig config) throws ServletException {
        super.init(config);
        CaptchaServlet.LOGGER.debug("Captcha servlet here (init)...");
    }

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * {@inheritDoc}
     */
    @Override
    protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {
        CaptchaServlet.LOGGER.debug("Captcha servlet here (doGet)...");

        // The captcha is created using the session id and the locale.
        final String token = req.getSession().getId();
        final Locale locale = req.getLocale();

        final byte[] captchaAsJpeg = this.getCaptchaChallenge(token, locale);
        if (captchaAsJpeg == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        resp.setHeader("Cache-Control", "no-store");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");

        final ServletOutputStream responseOutputStream = resp.getOutputStream();
        responseOutputStream.write(captchaAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * Creates a jpeg encoded captcha image defined by a token and a locale.<br/>
     * @param token the token defining the captcha
     * @param loc the locale
     * @return the captcha image as jpeg encoded byte array
     */
    private synchronized byte[] getCaptchaChallenge(final String token, final Locale loc) {
        byte[] captchaChallenge = null;

        // create the captcha challenge
        final BufferedImage challenge = CaptchaService.getInstance().getImageChallengeForID(token, loc);

        // transform image data into jpeg byte array
        final ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (final IOException e) {
            CaptchaServlet.LOGGER.error("Could not create captcha!", e);
        }

        /*
        final JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);

        try {
            jpegEncoder.encode(challenge);
        } catch (final ImageFormatException e) {
            CaptchaServlet.LOGGER.error("Could not create captcha!", e);
        } catch (final IOException e) {
            CaptchaServlet.LOGGER.error("Could not create captcha!", e);
        }
        */
        captchaChallenge = jpegOutputStream.toByteArray();
        return captchaChallenge;

    }
}
