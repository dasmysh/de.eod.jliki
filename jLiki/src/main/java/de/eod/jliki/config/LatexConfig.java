/**
 * File: LatexConfig.java
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
 * 17.11.2011: File creation.
 */
package de.eod.jliki.config;

import java.io.Serializable;

/**
 * This class holds the jLiki server latex configuration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 */
public class LatexConfig implements Serializable {

    /** holds serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Class construction.<br/>
     */
    public LatexConfig() {
    }

    /**
     * Returns the standard latex configuration.<br/>
     * @return the standard configuration
     */
    public static LatexConfig getStandardLatexConfig() {
        final LatexConfig cfg = new LatexConfig();
        return cfg;
    }
}
