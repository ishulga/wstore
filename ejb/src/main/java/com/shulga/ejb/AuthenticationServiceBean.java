package com.shulga.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.AuthenticationServiceRemote;
import com.shulga.model.Credentials;
import com.shulga.model.User;
import com.shulga.persistance.interfaces.UserPL;

@Stateless
public class AuthenticationServiceBean implements AuthenticationServiceRemote {
    @Inject
    private UserPL userPL;

    @Override
    public boolean login(Credentials creds) throws ServiceValidationException {
        User user = new User();
        user.setLogin(creds.getLogin());
        user.setPassword(creds.getPassword());
        User dbUser = userPL.getByLogin(creds.getLogin());
        if(dbUser==null){
            throw new ServiceValidationException("User does not exist");
        }
        if(!dbUser.getPassword().equals(user.getPassword())){
            throw new ServiceValidationException("Wrong credentials");
        }
        return true;
    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean register(Credentials creds) throws ServiceValidationException {
        User existedUser = null;
        try {
            existedUser = userPL.getByLogin(creds.getLogin());
            if (existedUser != null) {
                throw new ServiceValidationException("Such a user already exists");
            }
        } catch (NoResultException e) {
            // ignore, it marks that object does not exist in database
        }
        User user = new User();
        user.setLogin(creds.getLogin());
        user.setPassword(creds.getPassword());
        userPL.create(user);
        return true;
    }

}
