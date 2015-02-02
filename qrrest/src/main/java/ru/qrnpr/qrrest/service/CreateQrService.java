package ru.qrnpr.qrrest.service;

import com.google.zxing.WriterException;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.qrnpr.qrgenerator.helpers.PngHelper;
import ru.qrnpr.qrgenerator.models.QRModel;
import ru.qrnpr.qrgenerator.renderers.IQRRenderer;
import ru.qrnpr.qrgenerator.renderers.QRSimpleRenderer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.io.IOException;

/**
 * Created by PC on 02.02.2015.
 */
@Path("/createqr")
public class CreateQrService {
    private static final Logger LOG = LoggerFactory.getLogger(CreateQrService.class);

    // TODO temporary //////////////////////////////
    private final static String bgImg = "C:/Users/PC/IdeaProjects/qrnpr/temp/wsurf.png";
    private final static String resultFile = "C:/Users/PC/IdeaProjects/qrnpr/temp/result.png";
    ////////////////////////////////////////////////

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    // TODO переделать в json
    @POST
    @Path("/")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String getIt(
            @FormDataParam("xpos") String xpos,
            @FormDataParam("ypos") String ypos,
            @FormDataParam("size") String size,
            @FormDataParam("message") String message,
            @FormDataParam("color") String color
    ) {
        LOG.debug("Method POST. Path = /createqr");

        QRModel model = new QRModel();
//        model.setLogoPath(logoFile);
        // TODO change bgImg
        model.setBgimgPath(bgImg);
        model.setData(message);
        model.setPosX(Integer.parseInt(xpos));
        model.setPosY(Integer.parseInt(ypos));

        model.setSize(Integer.parseInt(size));

        int red = extractIntHex(color.substring(0, 2));
        int green = extractIntHex(color.substring(2, 4));
        int blue = extractIntHex(color.substring(4, 6));
        LOG.info("Color is red {}, green {}, blue {}", new Object[]{red, green, blue});
        model.setColor(new Color(red, green, blue, 100));

        PngHelper pngHelper = new PngHelper();
        IQRRenderer renderer = new QRSimpleRenderer();
        try {
            pngHelper.create(model, resultFile, renderer);
        } catch (IOException e) {
            LOG.warn("Exception during QR creation", e);
        } catch (WriterException e) {
            LOG.warn("Exception during QR creation", e);
        }
        LOG.info("done.");

        return "Got it!";
    }

    private int extractIntHex(String str) {
        return Integer.parseInt(str, 16);
    }

    @GET
    @Path("/")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String getIt() {
        LOG.info("GET, path = createqr/");
        return "GEEEEEET";
    }
}
