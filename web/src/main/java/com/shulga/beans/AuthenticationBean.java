package com.shulga.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.AuthenticationServiceRemote;
import com.shulga.model.Credentials;
import com.shulga.utils.ApplicationUtils;

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

    public void login() {
        if (StringUtils.isEmpty(login)) {
            ApplicationUtils.addMessage("Empty username");
        }
        if (StringUtils.isEmpty(password)) {
            ApplicationUtils.addMessage("Empty password");
        }
        creds = new Credentials();
        creds.setLogin(login);
        creds.setPassword(password);
        try {
            authService.login(creds);
        } catch (ServiceValidationException e) {
            ApplicationUtils.addMessage(e.getMessage());
        }
        ApplicationUtils.addMessage("Success login");
    }

    public void register() {
        if (!validateRegistration()) {
            return;
        }
        creds = new Credentials();
        creds.setLogin(login);
        creds.setPassword(password);
        try {
            authService.register(creds);
        } catch (ServiceValidationException e) {
            ApplicationUtils.addMessage(e.getMessage());
        }
        ApplicationUtils.addMessage("Success registration");
    }

    private boolean validateRegistration() {
        List<String> errorList = new ArrayList<String>();
        if (StringUtils.isEmpty(login)) {
            errorList.add("Empty username");
        }
        if (StringUtils.isEmpty(password)) {
            errorList.add("Empty password");
        }
        if (StringUtils.isEmpty(passwordConfirm)) {
            errorList.add("Empty passwordConfirm");
        }
        if (!password.equals(passwordConfirm)) {
            errorList.add("Passwords dont match");
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
