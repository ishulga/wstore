package com.shulga.model;

import java.io.Serializable;

public class CacheKey implements Serializable {
	private static final long serialVersionUID = 1L;
	private String key;
	private Long id;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
