package com.shulga.persistance.infinispan;

import java.util.Collection;
import java.util.List;

import com.shulga.model.User;
import com.shulga.persistance.annotations.CachePersistence;
import com.shulga.persistance.interfaces.UserPL;

@CachePersistence
public class UserCache extends GenericCache<User> implements UserPL {

	public UserCache() {
		super(User.class);
	}
	
	public User getByLogin(String login){
	    for (User user : cache.values()) {
            if(user.getLogin().equals(login)){
                return user;
            }
        }
	    return null;
	}

    @Override
    public Collection<User> getAll() {
        return cache.values();
    }
	
}
