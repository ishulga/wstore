package com.shulga.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.UserServiceRemote;
import com.shulga.model.Item;
import com.shulga.model.User;
import com.shulga.utils.ApplicationUtils;
import com.shulga.utils.WStoreMessages;

@Named
@SessionScoped
public class ItemBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String text;
    private User currentUser;
    @Inject
    private UserServiceRemote userService;
    @Inject
    private UserBean userBean;

    public String create() throws ServiceValidationException {
    	if(StringUtils.isEmpty(title) ||StringUtils.isEmpty(text)){
        	ApplicationUtils.addMessage(WStoreMessages.MESSAGES.emptyItemField());
        	return null;
        }
    	Item item = new Item();
    	item.setText(text);
    	item.setTitle(title);
    	Long currentUserId = (Long) ApplicationUtils.getSessionParam("currentUserId");
    	currentUser = userService.get(currentUserId);
    	currentUser.getSellItems().add(item);
    	userService.update(currentUser);
    	userBean.refresh(currentUser);
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
