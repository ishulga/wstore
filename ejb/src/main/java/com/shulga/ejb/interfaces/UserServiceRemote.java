package com.shulga.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.shulga.common.ValidationException;
import com.shulga.model.User;

@Local
public interface UserServiceRemote {
	
	Long create(User user) throws ValidationException;

    void update(User user);

    void delete(Long id);

    User get(Long id);

	List<User> getList(User qbe);
}
