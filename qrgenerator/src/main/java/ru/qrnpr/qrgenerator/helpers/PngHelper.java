package ru.qrnpr.qrgenerator.helpers;

import com.google.zxing.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.qrnpr.qrgenerator.models.QRModel;
import ru.qrnpr.qrgenerator.qrbuilders.QrCodeGenerator;
import ru.qrnpr.qrgenerator.renderers.IQRRenderer;
import ru.qrnpr.qrgenerator.utils.ImageUtils;
import ru.qrnpr.qrgenerator.utils.QRCodeChecker;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by vadim on 23.08.14.
 */
public class PngHelper {
    private static final Logger LOG = LoggerFactory.getLogger(PngHelper.class);

    public void create(QRModel model, String resultFile, IQRRenderer renderer) throws IOException, WriterException {
        BufferedImage resimg = ImageUtils.getImageFromFile(model.getBgimgPath());
        BufferedImage qrImage = QrCodeGenerator.createQrImage(model, renderer);
        resimg.createGraphics();
        resimg.getGraphics().drawImage(qrImage, 0, 0, null);

        boolean check = QRCodeChecker.isQRCodeCorrect(model.getData(), resimg);
        LOG.info("Check result is {}", check);

        ImageUtils.writeQrCodeToFile(resimg, model.getType(), resultFile);
        LOG.info("done.");
    }


}
