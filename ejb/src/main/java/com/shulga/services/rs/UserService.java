package com.shulga.services.rs;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.UserServiceRemote;
import com.shulga.model.User;

@Path(value = "/users")
public class UserService {

    @Inject
    private UserServiceRemote userService;

    @GET
    @Produces("text/xml")
    public List<User> allUsers() throws ServiceValidationException {
        return userService.getList(new User());
    }
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces("text/xml")
    public User getById(@PathParam("id") Long id) throws ServiceValidationException {
        return userService.get(id);
    }
    
}
