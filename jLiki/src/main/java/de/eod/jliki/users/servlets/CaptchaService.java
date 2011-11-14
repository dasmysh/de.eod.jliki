/**
 * File: CaptchaService.java
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

import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * The singleton captcha service.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public final class CaptchaService {

    /** holds the image captcha service. */
    private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService();

    /**
     * Returns the chaptcha services instance.<br/>
     * @return the chaptcha services instance
     */
    public static ImageCaptchaService getInstance() {
        return CaptchaService.instance;
    }

    /**
     * Prevent captcha service from being constructed.<br/>
     */
    private CaptchaService() {
    }
}
