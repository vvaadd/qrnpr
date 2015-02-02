package ru.qrnpr.qrrest.service;


import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import ru.qrnpr.qrcore.fileutils.IFileSave;
import ru.qrnpr.qrcore.fileutils.SimpleFileSave;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    public Response uploadFile(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

        // save the file to the server
        String fileName = contentDispositionHeader.getFileName();

        IFileSave fileSave = new SimpleFileSave();

        fileSave.saveFile(fileInputStream, fileName);

        String output = "File saved to server location : " + fileName;

        return Response.status(200).entity(output).build();

    }
}
