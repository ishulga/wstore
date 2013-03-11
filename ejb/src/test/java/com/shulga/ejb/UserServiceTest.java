package com.shulga.ejb;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.shulga.common.ValidationException;
import com.shulga.ejb.interfaces.UserServiceRemote;
import com.shulga.model.User;

@RunWith(Arquillian.class)
public class UserServiceTest {

	@Deployment
    public static Archive<?> createTestArchive() {
    	return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "com.shulga")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("test-ds.xml", "test-ds.xml");
    }

	@Inject
	private UserServiceRemote userService;

	@Test
	public void createUpdate() throws ValidationException {
		User user = new User();
		user.setName("Jack");
		Long id = userService.create(user);
		user = userService.get(id);
		assertEquals("Jack", user.getName());
		user.setName("Amy");
		userService.update(user);
		user = userService.get(id);
		assertEquals("Amy", user.getName());
	}

	@Test
	public void getByQBE() throws ValidationException {
		User user = new User();
		user.setName("Jack");
		user.setLastname("Welch");
		userService.create(user);
		user = new User();
		user.setName("Jack");
		user = userService.getList(user).get(0);
		assertEquals("Jack", user.getName());
		assertEquals("Welch", user.getLastname());
	}
	
}
