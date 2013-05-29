package com.shulga.ejb;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.AuthenticationServiceRemote;
import com.shulga.model.Credentials;
import com.shulga.model.User;
import com.shulga.persistance.interfaces.UserPL;

@Stateless
//@RolesAllowed({ "guest" })
//@SecurityDomain("other")
public class AuthenticationServiceBean implements AuthenticationServiceRemote {
	@Inject
	private UserPL userPL;

	@Resource
	private SessionContext ctx;

	@Override
	public Long login(Credentials creds) throws ServiceValidationException {
		User user = new User();
		user.setLogin(creds.getLogin());
		user.setPassword(creds.getPassword());
		User dbUser = null;
		try {
			dbUser = userPL.getByLogin(creds.getLogin());
		} catch (NoResultException e) {
			throw new ServiceValidationException("Wrong credentials");
		}
		if (!dbUser.getPassword().equals(user.getPassword())) {
			throw new ServiceValidationException("Wrong credentials");
		}
		return dbUser.getId();
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
	}

	@Override
	public Long register(Credentials creds) throws ServiceValidationException {
		Principal principal = ctx.getCallerPrincipal();
		User existedUser = null;
		try {
			existedUser = userPL.getByLogin(creds.getLogin());
			if (existedUser != null) {
				throw new ServiceValidationException(
						"Such a user already exists");
			}
		} catch (NoResultException e) {
			// ignore, it marks that object does not exist in database
		}
		User user = new User();
		user.setLogin(creds.getLogin());
		user.setPassword(creds.getPassword());
		return userPL.create(user);
	}

	@Override
	public String getSecurityInfo() {
		// Session context injected using the resource annotation
		Principal principal = ctx.getCallerPrincipal();

		return principal.toString();
	}

}
