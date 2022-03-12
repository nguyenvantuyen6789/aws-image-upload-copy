package com.amigoscode.awsimageupload.profile;

import com.amigoscode.awsimageupload.bucket.BucketName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService) {
        this.userProfileDataAccessService = userProfileDataAccessService;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        // 1. Check if image is not empty
//        isFileEmpty(file);
//        // 2. If file is an image
//        isImage(file);
//
//        // 3. The user exists in our database
//        UserProfile user = getUserProfileOrThrow(userProfileId);
//
//        // 4. Grab some metadata from file if any
//        Map<String, String> metadata = extractMetadata(file);
//
//        // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
//        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
//        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
//
//        try {
//            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
//            user.setUserProfileImageLink(filename);
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }

    }
}
