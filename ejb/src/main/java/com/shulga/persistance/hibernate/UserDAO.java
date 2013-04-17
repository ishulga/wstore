package com.shulga.persistance.hibernate;

import java.util.Collection;
import java.util.List;

import com.shulga.model.User;
import com.shulga.persistance.interfaces.UserPL;

public class UserDAO extends GenericPL<User> implements UserPL {

	public UserDAO() {
		super(User.class);
	}
	
	public User getByLogin(String login){
	    return (User) em.createQuery("select u from User u where u.login =:login").setParameter("login", login).getSingleResult();
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAll(User user) {
        return  getCriteria(user).list();
    }

	@SuppressWarnings("unchecked")
	@Override
	public Collection<User> getAll() {
		return getCriteria(new User()).list();
	}

	
}
