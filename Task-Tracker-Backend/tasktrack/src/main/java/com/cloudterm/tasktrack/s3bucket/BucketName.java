package com.cloudterm.tasktrack.s3bucket;

public enum BucketName {

    PROFILE_IMAGE("tasktracker-file-upload");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
