package com.shulga.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.UserServiceRemote;
import com.shulga.model.User;
import com.shulga.persistance.annotations.CachePersistence;
import com.shulga.persistance.annotations.DatabasePersistence;
import com.shulga.persistance.interfaces.UserPL;

@Stateless
public class UserServiceBean implements UserServiceRemote {
    // TODO make it work with interface, using cdi
    @Inject
    @CachePersistence
    private UserPL userPL;

    @Override
    public Long create(User user) throws ServiceValidationException {
        return userPL.create(user);
    }

    @Override
    public void update(User user) {
        userPL.update(user);
    }

    @Override
    public void delete(Long id) {
        // TODO use a common style for all war clients
        userPL.delete(id.toString());
    }

    @Override
    public User get(Long id) {
        // TODO use a common style for all war clients
        return userPL.get(id.toString());
    }
    
    @Override
    public Collection<User> getAll(User user) {
        return userPL.getAll(user);
    }

    @Override
    public User getByLogin(String login) {
        return userPL.getByLogin(login);
    }

	@Override
	public Collection<User> getAll() {
		return userPL.getAll();
	}

}
