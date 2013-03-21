package com.shulga.services.ws;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.UserServiceRemote;
import com.shulga.model.User;

//TODO Get deeper understanding of Arquillian
@Ignore
@RunWith(Arquillian.class)
public class UserEndpointTest {
    /**
     * The location of the WebApp source folder so we know where to find the web.xml when deploying using Arquillian.
     */
    private static final String WEBAPP_SRC = "src/main/webapp";
    /**
     * The name of the WAR Archive that will be used by Arquillian to deploy the application.
     */
    private static final String APP_NAME = "jboss-as-helloworld-ws";
    /**
     * The path of the WSDL endpoint in relation to the deployed web application.
     */
    private static final String WSDL_PATH = "HelloWorldService?wsdl";

    @ArquillianResource
    private URL deploymentUrl;

    private UserEndpoint client;
    @Inject
    private UserServiceRemote userService;

    @Deployment(testable = false)
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "jboss-app.war").addPackages(true, "com.shulga")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").addAsWebInfResource("test-ds.xml", "test-ds.xml").addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/web.xml"));
    }

    @Before
    public void setup() throws MalformedURLException, ServiceValidationException {
        client = new UserClient(new URL(deploymentUrl, WSDL_PATH));
        userService.create(new User("userLogin","userName"));
    }

    @Test
    public void testHello() {
        System.out.println("[Client] Requesting the WebService to say Hello.");

        // Get a response from the WebService
        final String response = client.getUserNames();
        assertEquals(response, "userName,");

        System.out.println("[WebService] " + response);

    }

    @Test
    public void testHelloName() {
        System.out.println("[Client] Requesting the WebService to say Hello to John.");

        // Get a response from the WebService
        final String response = client.getUsername("userLogin");
        assertEquals(response, "userName");

        System.out.println("[WebService] " + response);
    }

}
