package com.amigoscode.awsimageupload.service;

import com.amigoscode.awsimageupload.bucket.BucketName;
import com.amigoscode.awsimageupload.filestore.FileStore;
import com.amigoscode.awsimageupload.profile.UserDTO;
import com.amigoscode.awsimageupload.profile.UserProfileDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserService {

    private final UserProfileDataAccessService userProfileDataAccessService;

    private final FileStore fileStore;

    @Autowired
    public UserService(UserProfileDataAccessService userProfileDataAccessService,
                       FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    public List<UserDTO> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        // 1. Check if image is not empty
        isFileEmpty(file);
        // 2. If file is an image
        isImage(file);

        // 3. The user exists in our database
        List<UserDTO> userProfiles = userProfileDataAccessService.getUserProfiles();
        System.out.println(userProfiles.get(0).getUserProfileId());
        System.out.println(userProfiles.get(0).getUsername());
        System.out.println(userProfiles.get(1).getUserProfileId());
        System.out.println(userProfiles.get(1).getUsername());
//        UserProfile user = getUserProfileOrThrow(userProfileId, userProfiles);
        UserDTO user = userProfiles.get(0);

        // 4. Grab some metadata from file if any
        Map<String, String> metadata = extractMetadata(file);

        // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            user.setUserProfileImageLink(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserDTO getUserProfileOrThrow(UUID userProfileId, List<UserDTO> userProfiles) {
        return userProfiles.stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
//            throw new IllegalStateException("File must be an image");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Can not upload empty file [ " + file.getSize() + " ]");
        }
    }

}
