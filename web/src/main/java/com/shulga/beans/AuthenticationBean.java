package com.shulga.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Message;
import org.jboss.logging.Messages;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.AuthenticationServiceRemote;
import com.shulga.model.Credentials;
import com.shulga.utils.ApplicationUtils;
import com.shulga.utils.WStoreMessages;

/**
 * CDI beans is used for JSF implementation
 * 
 * @author ievgen.shulga
 */
@Named(value = "authBean")
@SessionScoped
public class AuthenticationBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private AuthenticationServiceRemote authService;
    private Credentials creds;
    private String login;
    private String password;
    private String passwordConfirm;
    private String locale;
    

    public String login() {
        if (StringUtils.isEmpty(login)) {
            ApplicationUtils.addMessage(WStoreMessages.MESSAGES.emptyLogin());
            return null;
        }
        if (StringUtils.isEmpty(password)) {
            ApplicationUtils.addMessage(WStoreMessages.MESSAGES.emptyPassword());
            return null;
        }
        creds = new Credentials();
        creds.setLogin(login);
        creds.setPassword(password);
        try {
            authService.login(creds);
        } catch (ServiceValidationException e) {
            ApplicationUtils.addMessage(e.getMessage());
        }
        return View.PERSONAL.page();
    }

    public String register() {
        if (!validateRegistration()) {
            return null;
        }
        creds = new Credentials();
        creds.setLogin(login);
        creds.setPassword(password);
        try {
            authService.register(creds);
        } catch (ServiceValidationException e) {
            ApplicationUtils.addMessage(e.getMessage());
        }
        return View.PERSONAL.page();
    }

    private boolean validateRegistration() {
        List<String> errorList = new ArrayList<String>();
        if (StringUtils.isEmpty(login)) {
            errorList.add(WStoreMessages.MESSAGES.emptyLogin());
        }
        if (StringUtils.isEmpty(password)) {
            errorList.add(WStoreMessages.MESSAGES.emptyPassword());
        }
        if (StringUtils.isEmpty(passwordConfirm)) {
            errorList.add(WStoreMessages.MESSAGES.emptyPassConfirm());
        }
        if (!password.equals(passwordConfirm)) {
            errorList.add(WStoreMessages.MESSAGES.passwordDontMatch());
        }
        if (errorList.isEmpty()) {
            return true;
        }
        for (String message : errorList) {
            ApplicationUtils.addMessage(message);
        }
        return false;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
