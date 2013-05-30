package com.shulga.authentification;

import javax.security.auth.login.LoginException;
import org.jboss.security.auth.spi.UsersRolesLoginModule;

public class WstoreLoginModule extends UsersRolesLoginModule {

	public boolean login() throws LoginException {
		
		System.out.println();
		return super.login();
	}

}
