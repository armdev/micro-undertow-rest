package com.backend.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.backend.bean.AnyBean;
import com.backend.entities.Contact;
import com.backend.web.facades.ContactRegistration;
import com.backend.web.facades.ContactRepository;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/backend")
public class RESTService implements Resource {

    @Inject
    private ContactRepository repository;

    @Inject
    private ContactRegistration registration;

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contact lookupContactById(@PathParam("id") Long id) {
        Contact contact = repository.findById(id);
        if (contact == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return contact;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Hello Any Test";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/json")
    public AnyBean json() {
        return new AnyBean();
    }

}
