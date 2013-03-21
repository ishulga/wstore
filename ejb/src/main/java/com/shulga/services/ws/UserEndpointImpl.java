package com.shulga.services.ws;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import com.shulga.ejb.UserServiceBean;
import com.shulga.ejb.interfaces.UserServiceRemote;
import com.shulga.model.User;

@WebService(endpointInterface = "com.shulga.services.ws.UserEndpoint", name = "UserService", portName = "UserService", serviceName = "UserService", targetNamespace = "http://www.wstore.com/com/shulga/ws/UserService")
public class UserEndpointImpl implements UserEndpoint {

    @Inject
    private UserServiceRemote userService;

    @Override
    public String getUserNames() {
        List<User> list = userService.getList(new User());
        StringBuffer sb = new StringBuffer();
        for (User user : list) {
            sb.append(user.getName() + ",");
        }
        return sb.toString();
    }

    @Override
    public String getUsername(String name) {
        User user = userService.getByLogin(name);
        return user.getName();
    }

}
