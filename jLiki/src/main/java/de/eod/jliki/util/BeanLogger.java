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
package de.eod.jliki.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

/**
 * Logger class for beans which writes log messages to jsf messages element.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
public class BeanLogger {

    /** holds the logger. */
    private final Logger logger;

    /**
     * Creates the BeanLogger.<br/>
     * @param clazz the class to log for
     */
    public BeanLogger(final Class<?> clazz) {
        this.logger = Logger.getLogger(clazz);
    }

    /**
     * Log message with debug level.<br/>
     * @param message the message to log
     */
    public final void debug(final String message) {
        final FacesMessage facesMsg = new FacesMessage(message, "[DEBUG] " + message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        this.logger.debug(message);
    }

    /**
     * Log message with debug level.<br/>
     * @param message the message to log
     * @param t the thrown exception to log
     */
    public final void debug(final String message, final Throwable t) {
        final FacesMessage facesMsg = new FacesMessage(message, "[DEBUG] " + message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        this.logger.debug(message, t);
    }

    /**
     * Log message with info level.<br/>
     * @param message the message to log
     */
    public final void info(final String message) {
        final FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "[INFO]  " + message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        this.logger.info(message);

    }

    /**
     * Log message with info level.<br/>
     * @param message the message to log
     * @param t the thrown exception to log
     */
    public final void info(final String message, final Throwable t) {
        final FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "[INFO]  " + message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        this.logger.info(message, t);
    }

    /**
     * Log message with warn level.<br/>
     * @param message the message to log
     */
    public final void warn(final String message) {
        final FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, message, "[WARN]  " + message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        this.logger.warn(message);
    }

    /**
     * Log message with warn level.<br/>
     * @param message the message to log
     * @param t the thrown exception to log
     */
    public final void warn(final String message, final Throwable t) {
        final FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, message, "[WARN]  " + message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        this.logger.warn(message, t);
    }

    /**
     * Log message with error level.<br/>
     * @param message the message to log
     */
    public final void error(final String message) {
        final FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "[ERROR] " + message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        this.logger.error(message);
    }

    /**
     * Log message with error level.<br/>
     * @param message the message to log
     * @param t the thrown exception to log
     */
    public final void error(final String message, final Throwable t) {
        final FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "[ERROR] " + message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        this.logger.error(message, t);
    }

    /**
     * Log message with fatal level.<br/>
     * @param message the message to log
     */
    public final void fatal(final String message) {
        final FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, message, "[FATAL] " + message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        this.logger.fatal(message);
    }

    /**
     * Log message with fatal level.<br/>
     * @param message the message to log
     * @param t the thrown exception to log
     */
    public final void fatal(final String message, final Throwable t) {
        final FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, message, "[FATAL] " + message);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        this.logger.fatal(message, t);
    }
}
