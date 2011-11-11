/**
 * File: PasswordHashUtility.java
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
 * 10.11.2011: File creation.
 */
package de.eod.jliki.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * Helper class for password hashes.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public final class PasswordHashUtility {

    /** holds the logger. */
    private static final Logger LOGGER = Logger.getLogger(PasswordHashUtility.class);
    /** holds the hash algorithm. */
    private static final String ALGORITHM = "SHA-256";
    /** holds the used character encoding. */
    private static final String ENCODING = "UTF-8";
    /** holds the number generation algorithm for the salt. */
    private static final String RNGALGORITHM = "SHA1PRNG";
    /** holds the size (in bytes) of the salt. */
    private static final int SALT_SIZE = 8;
    /** holds the number of iterations for password hashing. */
    private static final int NUM_ITERAITONS = 1000;

    /**
     * Prevents class from constructing.<br/>
     */
    private PasswordHashUtility() {
    }

    /**
     * Generates a hash string (url save) from the given data.<br/>
     * @param data the data to hash
     * @return a string in url save format
     */
    public static String generateHashForUrl(final String data) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(ALGORITHM);
        } catch (final NoSuchAlgorithmException e) {
            LOGGER.fatal("Hash algorithm not found: " + ALGORITHM, e);
            return "";
        }
        digest.reset();
        byte[] hashBytes;
        try {
            hashBytes = digest.digest(data.getBytes(ENCODING));
        } catch (final UnsupportedEncodingException e) {
            LOGGER.fatal("Character encoding not supported: " + ENCODING, e);
            return "";
        }

        final String hashString = Base64.encodeBase64URLSafeString(hashBytes);
        return hashString;
    }

    /**
     * Generates a salt for password hashing.<br/>
     * @return the salt
     */
    public static byte[] generateSalt() {
        SecureRandom random;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (final NoSuchAlgorithmException e) {
            LOGGER.fatal("Number generation algorithm not found: " + RNGALGORITHM, e);
            return new byte[SALT_SIZE];
        }
        final byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Builds a hash from a password.<br/>
     * @param password the password
     * @param salt the salt
     * @return the passwords hash
     */
    public static String hashPassword(final String password, final byte[] salt) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(ALGORITHM);
        } catch (final NoSuchAlgorithmException e) {
            LOGGER.fatal("Hash algorithm not found: " + ALGORITHM, e);
            return "";
        }
        digest.reset();
        digest.update(salt);
        byte[] hashBytes;
        try {
            hashBytes = digest.digest(password.getBytes(ENCODING));
        } catch (final UnsupportedEncodingException e) {
            LOGGER.fatal("Character encoding not supported: " + ENCODING, e);
            return "";
        }

        for (int i = 0; i < NUM_ITERAITONS; i++) {
            digest.reset();
            hashBytes = digest.digest(hashBytes);
        }

        final String hashString = Base64.encodeBase64String(hashBytes);
        return hashString;
    }

    /**
     * Checks if a password matches with a hash.<br/>
     * @param password the password
     * @param hashString the hash to match
     * @param salt the salt
     * @return true if the password matches the hash
     */
    public static boolean verifyPassword(final String password, final String hashString, final byte[] salt) {
        return hashPassword(password, salt).equals(hashString);
    }

}
