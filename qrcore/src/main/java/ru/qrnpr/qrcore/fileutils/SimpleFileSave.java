package ru.qrnpr.qrcore.fileutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by vadim on 23.12.14.
 */
public class SimpleFileSave implements IFileSave {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleFileSave.class);

    @Override
    public String saveFile(InputStream fileInputStream, String fileName) {
        LOG.info("SimpleFileSave saveFile with fileName - " + fileName);
        try {
            String filePath = "/home/vadim/tmp/" + fileName;

            OutputStream outpuStream = new FileOutputStream(new File(filePath));
            int read = 0;
            byte[] bytes = new byte[1024];

            outpuStream = new FileOutputStream(new File(filePath));
            while ((read = fileInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();

            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
