/**
 * File: UserRegisterBean.java
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
 * 08.11.2011: File creation.
 */
package de.eod.jliki.users.jsfbeans;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.hibernate.validator.constraints.NotBlank;

import de.eod.jliki.config.ConfigManager;
import de.eod.jliki.users.dbbeans.User;
import de.eod.jliki.users.utils.UserDBHelper;
import de.eod.jliki.util.BeanLogger;
import de.eod.jliki.util.Messages;

/**
 * Bean that manages user registration.<br/>
 * @author <a href="mailto:sebastian.maisch@googlemail.com">Sebastian Maisch</a>
 *
 */
@ManagedBean(name = "userRegisterBean")
@RequestScoped
public class UserRegisterBean implements Cloneable, Serializable {

    /** holds default serialization UID. */
    private static final long serialVersionUID = 1L;
    /** holds the logger. */
    private static final BeanLogger LOGGER = new BeanLogger(UserRegisterBean.class);

    /** holds the username. */
    // CHECKSTYLE IGNORE
    @Size(min = 3, max = 25, message = "{register.panel.invalid.username}")
    private String username;
    /** holds the password. */
    // CHECKSTYLE IGNORE
    @Size(min = 6, max = 50, message = "{register.panel.invalid.password}")
    private String password;
    /** holds the password confirm. */
    // CHECKSTYLE IGNORE
    @Size(min = 6, max = 50, message = "{register.panel.invalid.password}")
    private String confirm;
    /** holds the users email. */
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$", message = "{register.panel.invalid.email}")
    private String email;
    /** holds the users first name. */
    @NotBlank(message = "{register.panel.invalid.firstname}")
    private String firstname;
    /** holds the users last name. */
    @NotBlank(message = "{register.panel.invalid.lastname}")
    private String lastname;
    /** holds the captcha. */
    @NotBlank(message = "{register.panel.invalid.captcha}")
    private String captcha;
    /** holds the terms of use check. */
    @AssertTrue(message = "{register.panel.invalid.tou}")
    private boolean termsOfUse;
    /** holds the registered flag, it is unset if a registration failed. */
    private boolean success = false;

    /**
     * Makes sure password and confirm are the same.<br/>
     * @return true if password and confirm match
     */
    @AssertTrue(message = "{register.panel.invalid.confirm.password}")
    public final boolean isPasswordsEquals() {
        return this.password.equals(this.confirm);
    }

    /**
     * @return the username
     */
    public final String getUsername() {
        return this.username;
    }

    /**
     * @param theUsername the username to set
     */
    public final void setUsername(final String theUsername) {
        this.username = theUsername;
    }

    /**
     * @return the password
     */
    public final String getPassword() {
        return this.password;
    }

    /**
     * @param thePassword the password to set
     */
    public final void setPassword(final String thePassword) {
        this.password = thePassword;
    }

    /**
     * @return the confirm
     */
    public final String getConfirm() {
        return this.confirm;
    }

    /**
     * @param theConfirm the confirm to set
     */
    public final void setConfirm(final String theConfirm) {
        this.confirm = theConfirm;
    }

    /**
     * @return the email
     */
    public final String getEmail() {
        return this.email;
    }

    /**
     * @param theEmail the email to set
     */
    public final void setEmail(final String theEmail) {
        this.email = theEmail;
    }

    /**
     * @return the firstname
     */
    public final String getFirstname() {
        return this.firstname;
    }

    /**
     * @param theFirstname the firstname to set
     */
    public final void setFirstname(final String theFirstname) {
        this.firstname = theFirstname;
    }

    /**
     * @return the lastname
     */
    public final String getLastname() {
        return this.lastname;
    }

    /**
     * @param theLastname the lastname to set
     */
    public final void setLastname(final String theLastname) {
        this.lastname = theLastname;
    }

    /**
     * getter for property captcha
     * @return returns the captcha.
    */
    public final String getCaptcha() {
        return this.captcha;
    }

    /**
     * setter for property captcha
     * @param theCaptcha The captcha to set.
     */
    public final void setCaptcha(final String theCaptcha) {
        this.captcha = theCaptcha;
    }

    /**
     * getter for property termsOfUse
     * @return returns the termsOfUse.
    */
    public final boolean isTermsOfUse() {
        return this.termsOfUse;
    }

    /**
     * setter for property termsOfUse
     * @param theTermsOfUse The termsOfUse to set.
     */
    public final void setTermsOfUse(final boolean theTermsOfUse) {
        this.termsOfUse = theTermsOfUse;
    }

    /**
     * getter for property success
     * @return returns the success.
    */
    public final boolean isSuccess() {
        return this.success;
    }

    /**
     * @see java.lang.Object#clone()
     * {@inheritDoc}
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Adds a new user to the jLiki database.<br/>
     */
    public final void addNewUser() {
        final User newUser = new User(this.username, this.password, this.email, this.firstname, this.lastname);
        final String userHash = UserDBHelper.addUserToDB(newUser);

        if (userHash == null) {
            Messages.addFacesMessage(null, FacesMessage.SEVERITY_ERROR, "message.user.register.failed", this.username);
            return;
        }

        UserRegisterBean.LOGGER.debug("Adding user: " + newUser.toString());

        final FacesContext fc = FacesContext.getCurrentInstance();
        final HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        final ResourceBundle mails = ResourceBundle.getBundle("de.eod.jliki.EMailMessages", fc.getViewRoot()
                .getLocale());
        final String activateEMailTemplate = mails.getString("user.registration.email");
        final StringBuffer url = request.getRequestURL();
        final String serverUrl = url.substring(0, url.lastIndexOf("/"));

        UserRegisterBean.LOGGER.debug("Generated key for user: \"" + userHash + "\"");

        final String emsLink = serverUrl + "/activate.xhtml?user=" + newUser.getName() + "&key=" + userHash;
        final String emsLikiName = ConfigManager.getInstance().getConfig().getPageConfig().getPageName();
        final String emsEMailText = MessageFormat.format(activateEMailTemplate, emsLikiName, this.firstname,
                this.lastname, this.username, emsLink);

        final String emsHost = ConfigManager.getInstance().getConfig().getEmailConfig().getHostname();
        final int emsPort = ConfigManager.getInstance().getConfig().getEmailConfig().getPort();
        final String emsUser = ConfigManager.getInstance().getConfig().getEmailConfig().getUsername();
        final String emsPass = ConfigManager.getInstance().getConfig().getEmailConfig().getPassword();
        final boolean emsTSL = ConfigManager.getInstance().getConfig().getEmailConfig().isUseTLS();
        final String emsSender = ConfigManager.getInstance().getConfig().getEmailConfig().getSenderAddress();

        final Email activateEmail = new SimpleEmail();
        activateEmail.setHostName(emsHost);
        activateEmail.setSmtpPort(emsPort);
        activateEmail.setAuthentication(emsUser, emsPass);
        activateEmail.setTLS(emsTSL);
        try {
            activateEmail.setFrom(emsSender);
            activateEmail.setSubject("Activate jLiki Account");
            activateEmail.setMsg(emsEMailText);
            activateEmail.addTo(this.email);
            activateEmail.send();
        } catch (final EmailException e) {
            UserRegisterBean.LOGGER.error("Sending activation eMail failed!", e);
            return;
        }

        this.username = "";
        this.password = "";
        this.confirm = "";
        this.email = "";
        this.firstname = "";
        this.lastname = "";
        this.captcha = "";
        this.termsOfUse = false;
        this.success = true;

        Messages.addFacesMessage(null, FacesMessage.SEVERITY_INFO, "message.user.registered", this.username);
    }
}
