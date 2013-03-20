package com.shulga.utils;

import javax.faces.context.FacesContext;

import org.jboss.logging.Message;
import org.jboss.logging.MessageBundle;
import org.jboss.logging.Messages;

@MessageBundle(projectCode = "")
public interface WStoreMessages {
    WStoreMessages MESSAGES = Messages.getBundle(WStoreMessages.class, FacesContext.getCurrentInstance().getViewRoot()
            .getLocale());

    @Message("Empty login")
    String emptyLogin();

    @Message("Empty Password")
    String emptyPassword();

    @Message("Empty Password Confirmation")
    String emptyPassConfirm();

    @Message("Password dont match")
    String passwordDontMatch();

}
