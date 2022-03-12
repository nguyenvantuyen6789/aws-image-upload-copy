package com.amigoscode.awsimageupload.datastore;

import com.amigoscode.awsimageupload.profile.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    public List<UserDTO> getUserProfiles() {
        UserDTO userProfile01 = new UserDTO(UUID.randomUUID(), "janetjones", null);
        UserDTO userProfile02 = new UserDTO(UUID.randomUUID(), "antoniojunior", null);

        List<UserDTO> USER_PROFILES = new ArrayList<>();
        USER_PROFILES.add(userProfile01);
        USER_PROFILES.add(userProfile02);
        return USER_PROFILES;
    }

}
