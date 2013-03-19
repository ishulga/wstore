package com.shulga.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.shulga.model.Item;

@Named
@SessionScoped
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Item> sellItems = new ArrayList<Item>();
    private List<Item> boughtItems = new ArrayList<Item>();

    public List<Item> getSellItems() {
        return sellItems;
    }

    public void setSellItems(List<Item> sellItems) {
        this.sellItems = sellItems;
    }

    public List<Item> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(List<Item> boughtItems) {
        this.boughtItems = boughtItems;
    }

}
