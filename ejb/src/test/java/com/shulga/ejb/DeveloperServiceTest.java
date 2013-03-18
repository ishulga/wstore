package com.shulga.ejb;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.DeveloperServiceRemote;
import com.shulga.model.Developer;
import com.shulga.persistance.DeveloperPL;
import com.shulga.persistance.GenericPL;

@RunWith(Arquillian.class)
public class DeveloperServiceTest {

	@Deployment
    public static Archive<?> createTestArchive() {
    	return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "com.shulga")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("test-ds.xml", "test-ds.xml");
    }

	@Inject
	private DeveloperServiceRemote devService;

	@Test
	public void createUpdate() throws ServiceValidationException {
		Developer dev = new Developer();
		dev.setName("Jack");
		Long id = devService.create(dev);
		Developer developer = devService.get(id);
		assertEquals("Jack", developer.getName());
		developer.setName("Amy");
		devService.update(developer);
		developer = devService.get(id);
		assertEquals("Amy", developer.getName());
	}

	@Test
	public void getByQBE() throws ServiceValidationException {
		Developer dev = new Developer();
		dev.setName("Jack");
		dev.setLastname("Welch");
		devService.create(dev);
		dev = new Developer();
		dev.setName("Jack");
		dev = devService.getList(dev).get(0);
		assertEquals("Jack", dev.getName());
		assertEquals("Welch", dev.getLastname());
	}
	
}
