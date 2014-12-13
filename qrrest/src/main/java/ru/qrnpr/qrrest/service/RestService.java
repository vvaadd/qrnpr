package ru.qrnpr.qrrest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by vadim on 13.11.14.
 */
@Path("/")
public class RestService {
    private static final Logger LOG = LoggerFactory.getLogger(RestService.class);

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        LOG.debug("Method GET. Path = /*");
        return "Got it!";
    }
}
