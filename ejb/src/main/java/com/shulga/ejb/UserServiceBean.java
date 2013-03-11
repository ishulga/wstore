package com.shulga.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.shulga.common.ValidationException;
import com.shulga.ejb.interfaces.UserServiceRemote;
import com.shulga.model.User;
import com.shulga.persistance.UserPL;

@Stateless
public class UserServiceBean implements UserServiceRemote {
	@Inject
	private UserPL userPL;

	@Override
	public Long create(User user) throws ValidationException {
		return userPL.create(user);
	}

	@Override
	public void update(User user) {
		userPL.update(user);
	}

	@Override
	public void delete(Long id) {
		userPL.delete(id);
	}

	@Override
	public User get(Long id) {
		return userPL.get(id);
	}

	@Override
	public List<User> getList(User qbe) {
		return userPL.getListByQBE(qbe);
	}

}
