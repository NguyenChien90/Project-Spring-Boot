package com.ojt.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.annotation.PostConstruct;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AmazonClient {
    private AmazonS3 s3client;
    private final String endpointUrl = "https://s3.us-east-2.amazonaws.com";
    private final String bucketName = "thuctap-t6";
    private final String accessKey = "AKIA2JIYSHFO73AQ76WL";
    private final String secretKey = "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";


    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }
    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead))
        ;

    }
    public String uploadFile(File file, String fileName) {

        String fileUrl = "";
        try {
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }
}
