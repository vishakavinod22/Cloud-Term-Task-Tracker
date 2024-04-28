package com.cloudterm.tasktrack.s3bucket;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {
    private final AmazonS3 amazonS3;

    @Autowired
    public FileStore(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    // Method to save file to amazon s3
    public void save(String path, String fileName, Optional<Map<String,String>> optionalMetadata, InputStream inputStream){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if(!map.isEmpty()){
                map.forEach(objectMetadata:: addUserMetadata);
            }
        });

        try{
            objectMetadata.setContentLength(inputStream.available());
            amazonS3.putObject(path, fileName, inputStream, objectMetadata);
            System.out.println("File uploaded");
        } catch (AmazonServiceException e){
            System.out.println("Failed to store in s3: " + e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
