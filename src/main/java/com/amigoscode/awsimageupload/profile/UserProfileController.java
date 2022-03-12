package com.amigoscode.awsimageupload.profile;

import com.amigoscode.awsimageupload.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("*")
public class UserProfileController {

    private final UserService userProfileService;

    @Autowired
    public UserProfileController(UserService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public List<UserDTO> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }

    @PostMapping(path = "{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId,
                                       @RequestParam("file") MultipartFile file) {
        userProfileService.uploadUserProfileImage(userProfileId, file);
    }

}
