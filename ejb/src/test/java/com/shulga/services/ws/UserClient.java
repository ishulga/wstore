package com.shulga.services.ws;


import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * A Client stub to the HelloWorld JAX-WS Web Service.
 * 
 */
public class UserClient implements UserEndpoint {
    private UserEndpoint userEndpoint;

    /**
     * Default constructor
     * 
     * @param url The URL to the WSDL endpoint.
     */
    public UserClient(final URL wsdlUrl) {
        QName serviceName = new QName("http://www.wstore.com/com/shulga/ws/UserService", "UserService");

        Service service = Service.create(wsdlUrl, serviceName);
        userEndpoint = service.getPort(UserEndpoint.class);
        assert (userEndpoint != null);
    }
    
    /**
     * Default constructor
     * 
     * @param url The URL to the Hello World WSDL endpoint.
     * @throws MalformedURLException if the WSDL url is malformed.
     */
    public UserClient(final String url) throws MalformedURLException {
        this(new URL(url));
    }

    @Override
    public String getUserNames() {
        return userEndpoint.getUserNames();
    }

    @Override
    public String getUsername(String login) {
        return userEndpoint.getUsername(login);
    }

}
