package ru.qrnpr.qrcore.fileutils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class S3FileSave implements IFileSave {
    private static final Logger LOG = LoggerFactory.getLogger(S3FileSave.class);

    private static String bucketName = "qrimagestore";

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("START");
        InputStream is = new FileInputStream("/home/vadim/tmp/volley.png");
        new S3FileSave().saveFile(is, "fsg.png");
        System.out.println("OK");
    }

    @Override
    public String saveFile(InputStream fileInputStream, String fileName) {
        AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
        try {
            LOG.info("Uploading a new object to S3 from a file");
            PutObjectResult result = s3client.putObject(bucketName, fileName, fileInputStream, new ObjectMetadata());

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            LOG.warn("Error Message:    " + ase.getMessage());
            LOG.warn("HTTP Status Code: " + ase.getStatusCode());
            LOG.warn("AWS Error Code:   " + ase.getErrorCode());
            LOG.warn("Error Type:       " + ase.getErrorType());
            LOG.warn("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            LOG.warn("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            LOG.warn("Error Message: " + ace.getMessage());
        }
        return null;
    }

}
