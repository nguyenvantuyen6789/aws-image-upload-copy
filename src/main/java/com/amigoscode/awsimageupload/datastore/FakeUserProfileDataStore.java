package com.amigoscode.awsimageupload.datastore;

import com.amigoscode.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    public List<UserProfile> getUserProfiles() {
        UserProfile userProfile01 =  new UserProfile(UUID.randomUUID(), "janetjones", null);
        UserProfile userProfile02 =  new UserProfile(UUID.randomUUID(), "antoniojunior", null);

        List<UserProfile> USER_PROFILES = new ArrayList<>();
        USER_PROFILES.add(userProfile01);
        USER_PROFILES.add(userProfile02);
        return USER_PROFILES;
    }

}
