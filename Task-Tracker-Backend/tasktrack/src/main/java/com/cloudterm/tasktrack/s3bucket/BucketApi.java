package com.cloudterm.tasktrack.s3bucket;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.cloudterm.tasktrack.repository.DbConstants.EC2_IP;

@RestController
@CrossOrigin(origins = EC2_IP)
public class BucketApi {

    private final FileStore fileStore;

    public BucketApi(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    // api to upload file to s3 bucket
    @PostMapping(path = "/tasktracker-file-upload/{userId}/file/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadFileToS3(@PathVariable("userId") int userId, @RequestParam("file") MultipartFile file) throws IOException {

        // check if file is empty or not
        if(file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file");
        }
        // check if user id is valid
        if(userId <= 0){
            throw new IllegalStateException("Invalid user id");
        }
        // get metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        // upload to s3
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userId);
        fileStore.save(path, file.getOriginalFilename(), Optional.of(metadata), file.getInputStream());

    }
}
