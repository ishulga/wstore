package com.shulga.persistance.interfaces;

import java.util.Collection;
import java.util.List;

import com.shulga.common.ServiceValidationException;
import com.shulga.model.User;

public interface UserPL {

    Long create(User user) throws ServiceValidationException;

    void update(User user);

    void delete(String id);

    User get(String id);

    User getByLogin(String login);
    
    Collection<User> getAll();
}
