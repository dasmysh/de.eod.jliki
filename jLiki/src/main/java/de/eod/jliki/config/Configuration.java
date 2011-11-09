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
 * 07.11.2011: File creation.
 */
package de.eod.jliki.config;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class holds the jLiki server configuration and is able to load from/save to file.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class Configuration {

    /** holds the page configuration. */
    private PageConfig pageConfig = new PageConfig();
    /** holds the database configuration. */
    private DBConfig dbConfig = new DBConfig();

    /** holds the database configuration. */
    //privat

    /**
     * Loads the configuration from file.<br/>
     * @param filename the filename
     * @return the configuration object loaded
     * @throws IOException in case of errors
     */
    public static Configuration loadFromFile(final String filename) throws IOException {
        Configuration config = null;
        final FileInputStream in = new FileInputStream(filename);
        final XMLDecoder d = new XMLDecoder(in);
        config = (Configuration) d.readObject();
        d.close();
        in.close();
        return config;

    }

    /**
     * Saves the configuration to file.<br/>
     * @param filename the filename
     * @throws IOException in case of errors
     */
    public final void saveToFile(final String filename) throws IOException {
        final File f = new File(filename);
        final File dir = f.getParentFile();
        if (dir != null && !dir.exists() && !dir.mkdirs()) {
            throw new IOException("Can not creat directories: " + dir.getAbsolutePath());
        }
        final FileOutputStream out = new FileOutputStream(f);
        final XMLEncoder enc = new XMLEncoder(out);
        enc.writeObject(this);
        enc.close();
        out.close();
    }

    /**
     * @return the pageConfig
     */
    public final PageConfig getPageConfig() {
        return this.pageConfig;
    }

    /**
     * @param thePageConfig the pageConfig to set
     */
    public final void setPageConfig(final PageConfig thePageConfig) {
        this.pageConfig = thePageConfig;
    }

    /**
     * @return the dbConfig
     */
    public final DBConfig getDbConfig() {
        return this.dbConfig;
    }

    /**
     * @param theDBConfig the dbConfig to set
     */
    public final void setDbConfig(final DBConfig theDBConfig) {
        this.dbConfig = theDBConfig;
    }

}
