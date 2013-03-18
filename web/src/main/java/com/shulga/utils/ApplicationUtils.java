package com.shulga.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ApplicationUtils {
    public static void addMessage(String message) {
        FacesMessage fMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(fMessage.getSummary(), fMessage);
    }
}
