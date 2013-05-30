package com.shulga.beans;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javassist.expr.NewArray;

import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.AuthenticationServiceRemote;
import com.shulga.ejb.interfaces.UserServiceRemote;
import com.shulga.model.Credentials;
import com.shulga.model.Item;
import com.shulga.model.User;
import com.shulga.utils.ApplicationUtils;
import com.shulga.utils.WStoreMessages;

/**
 * CDI beans is used for JSF implementation
 * 
 * @author ievgen.shulga
 */
@Named
@SessionScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private AuthenticationServiceRemote authService;
	@Inject
	private UserServiceRemote userService;
	private List<Item> sellItems = new ArrayList<Item>();
	private List<Item> boughtItems = new ArrayList<Item>();
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
			ApplicationUtils
					.addMessage(WStoreMessages.MESSAGES.emptyPassword());
			return null;
		}
		creds = new Credentials();
		creds.setLogin(login);
		creds.setPassword(password);
		Long id = null;
		try {
			id = authService.login(creds);
		} catch (ServiceValidationException e) {
			ApplicationUtils.addMessage(e.getMessage());
		}
		ApplicationUtils.putSessionParam("currentUserId", id);
		refresh(userService.get(id));
		return View.PERSONAL.page();
	}

	public void refresh(User user) {
		setSellItems(new ArrayList<Item>(user.getSellItems()));
		setBoughtItems(new ArrayList<Item>(user.getBoughtItems()));
	}

	public String register() {
		FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		if (!validateRegistration()) {
			return null;
		}
		creds = new Credentials();
		creds.setLogin(login);
		creds.setPassword(password);
		Long id = null;
		try {
			id = authService.register(creds);
		} catch (ServiceValidationException e) {
			ApplicationUtils.addMessage(e.getMessage());
		}
		ApplicationUtils.putSessionParam("currentUserId", id);
		refresh(userService.get(id));
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
