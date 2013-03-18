package com.shulga.persistance;

import com.shulga.model.User;

public class UserPL extends GenericPL<User> {

	public UserPL() {
		super(User.class);
	}
	
	public User getByLogin(String login){
	    return (User) em.createQuery("select u from User u where u.login =:login").setParameter("login", login).getSingleResult();
	}
	
}
