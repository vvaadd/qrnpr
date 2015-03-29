package ru.qrnpr.qrcore.fileutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.qrnpr.qrcore.constants.Params;

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
            String filePath = Params.FILE_PATH + fileName;

            OutputStream outputStream = new FileOutputStream(new File(filePath));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();

            return Params.FILE_STORE_URL + fileName;
        } catch (IOException e) {
            LOG.warn("Can't save file", e);
        }
        return null;
    }
}
