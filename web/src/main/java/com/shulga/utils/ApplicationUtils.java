package com.shulga.utils;

import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ApplicationUtils {
	public static void addMessage(String message) {
		FacesMessage fMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(fMessage.getSummary(),
				fMessage);
	}

	public static String getRequestParam(String name) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

	public static Object getSessionParam(String name) {
		Map<String, Object> map = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		for (Entry<String, Object> entry : map.entrySet()) {
			if (name.equals(entry.getKey())) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public static void putSessionParam(String key,Object value){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}

}
