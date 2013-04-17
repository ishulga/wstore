package com.shulga.ejb;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.UserServiceRemote;
import com.shulga.ejb.util.Deployments;
import com.shulga.model.Comment;
import com.shulga.model.Item;
import com.shulga.model.User;

//TODO currently not working with JDG
@RunWith(Arquillian.class)
public class UserServiceTest {

	@Deployment
    public static Archive<?> createTestArchive() {
    	return Deployments.baseDeployment();
    }

	@Inject
	private UserServiceRemote userService;

	@Test
	public void createUpdate() throws ServiceValidationException {
		User user = new User();
		user.setName("Jack");
		user.setLogin("Jack");
		Long id = userService.create(user);
		user = userService.get(id);
		assertEquals("Jack", user.getName());
		user.setName("Amy");
		userService.update(user);
		user = userService.get(id);
		assertEquals("Amy", user.getName());
	}

	//TODO make it work with datagrid
	@Test
	@Ignore
	public void getByQBE() throws ServiceValidationException {
		User user = new User();
		user.setName("Jack");
		user.setLogin("Jack");
		user.setLastname("Black");
		userService.create(user);
		user = new User();
		user.setLastname("Black");
		user = userService.getAll(user).iterator().next();
		assertEquals("Jack", user.getName());
		assertEquals("Black", user.getLastname());
	}
	@Test
    public void getByLogin() throws ServiceValidationException {
	    User user = new User();
	    String login = "testLogin";
        user.setLogin(login);
	    userService.create(user);
	    user = userService.getByLogin(login);
	    assertEquals(login,user.getLogin());
	}
	
	@Test
    public void userWithItems() throws ServiceValidationException {
        User user = new User();
        user.setName("Jack");
        user.setLogin("Jack");
        user.setLastname("Welch");
        Item boughtItem = new Item();
        boughtItem.setTitle("boughtItem");
        Comment boughtComment = new Comment();
        boughtComment.setTitle("boughtComment");
        boughtItem.getComments().add(boughtComment);
        Item sellItem = new Item();
        sellItem.setTitle("sellItem");
        Comment sellComment = new Comment();
        sellComment.setTitle("sellComment");
        sellItem.getComments().add(sellComment);
        user.getBoughtItems().add(boughtItem);
        user.getSellItems().add(sellItem);
        Long id = userService.create(user);
        user = userService.get(id);
        
        sellItem = user.getSellItems().iterator().next();
        boughtItem = user.getBoughtItems().iterator().next();
        
        assertEquals("Jack", user.getName());
        assertEquals("boughtItem", boughtItem.getTitle());
        assertEquals("sellItem", sellItem.getTitle());
        assertEquals("boughtComment", boughtItem.getComments().iterator().next().getTitle());
        assertEquals("sellComment", sellItem.getComments().iterator().next().getTitle());
	}
	
}
