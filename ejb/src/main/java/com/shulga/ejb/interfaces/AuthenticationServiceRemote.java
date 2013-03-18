package com.shulga.ejb.interfaces;

import javax.ejb.Local;

import com.shulga.common.ServiceValidationException;
import com.shulga.model.Credentials;
import com.shulga.model.User;

@Local
public interface AuthenticationServiceRemote {
    
    boolean login(Credentials creds) throws ServiceValidationException;

    void logout();

    boolean register(Credentials creds) throws ServiceValidationException;
}
