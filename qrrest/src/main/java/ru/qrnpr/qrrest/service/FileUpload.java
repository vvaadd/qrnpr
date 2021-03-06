package ru.qrnpr.qrrest.service;


import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import ru.qrnpr.qrcore.fileutils.IFileSave;
import ru.qrnpr.qrcore.fileutils.SimpleFileSave;
import ru.qrnpr.qrrest.model.BGImage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;


/**
 * Created by vadim on 22.12.14.
 */
@Path("/files")
public class FileUpload {
    /**
     * Upload a File
     */

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

        // save the file to the server
        String fileName = contentDispositionHeader.getFileName();

        IFileSave fileSave = new SimpleFileSave();
        String savedFileUrl = fileSave.saveFile(fileInputStream, fileName);
        BGImage bg = new BGImage();
        bg.setUrl(savedFileUrl);
        // TODO set width and height
        bg.setWidth("200");
        bg.setHeight("200");
        return Response.status(200).entity(bg).build();
    }
}
