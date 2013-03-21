package com.shulga.ejb;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.UserServiceRemote;
import com.shulga.model.Comment;
import com.shulga.model.Entry;
import com.shulga.model.Item;
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
	public void createUpdate() throws ServiceValidationException {
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
	public void getByQBE() throws ServiceValidationException {
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
