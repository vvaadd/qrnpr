package ru.qrnpr.qrrest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserRestService {
    private static final Logger LOG = LoggerFactory.getLogger(UserRestService.class);

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser() {
        LOG.debug("Method GET. Path = /*");
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = authentication.getName();
        return name;
    }
}
