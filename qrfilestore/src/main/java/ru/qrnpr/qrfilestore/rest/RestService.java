package ru.qrnpr.qrfilestore.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

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
    @Path("/{filename}")
    @Produces("image/png")
    public Response getFile(@PathParam("filename") String filename) {
        LOG.debug("Method GET. Path = /*");
        try {
            BufferedImage image = ImageIO.read(new File("D:/IdeaProjects/qrnpr/temp/" + filename));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageData = baos.toByteArray();
            return Response.ok(imageData).build();
        } catch (IOException e) {
            LOG.warn("Can't write image", e);
            return Response.serverError().build();
        }
    }
}
