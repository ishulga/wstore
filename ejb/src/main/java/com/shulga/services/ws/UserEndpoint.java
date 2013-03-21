package com.shulga.services.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.wstore.com/com/shulga/ws/UserService")
public interface UserEndpoint {
    
    @WebMethod
    String getUserNames();

    @WebMethod
    String getUsername(String login);
}
