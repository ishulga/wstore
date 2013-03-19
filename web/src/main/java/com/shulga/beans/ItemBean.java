package com.shulga.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ItemBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String text;

    public String create() {
        //TODO
        return View.PERSONAL.page();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
