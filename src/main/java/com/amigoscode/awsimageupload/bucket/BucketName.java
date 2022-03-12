package com.amigoscode.awsimageupload.bucket;

public enum BucketName {
    PROFILE_IMAGE("amigoscode-image-upload-123889");
    private String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }

}
