/**
 * Copyright (C) 2011 The jLiki Programming Team.
 *
 * This file is part of jLiki.
 *
 * jLiki is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Author: Sebastian Maisch
 * Last changes:
 * 08.11.2011: File creation.
 */
package de.eod.jliki;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Test class.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@ManagedBean
@RequestScoped
public class HelloPB {

    /** holds the name. */
    private String name = "";

    /**
     * Say hello + name that was entered.
     * @return Hello + name
     */
    public final String getHello() {
        if (this.name == null || this.name.length() < 1) {
            return null;
        }
        return "Hello " + this.name;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return this.name;
    }

    /**
     * @param theName the name to set
     */
    public final void setName(final String theName) {
        this.name = theName;
    }
}
