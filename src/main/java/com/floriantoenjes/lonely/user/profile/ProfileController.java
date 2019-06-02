package com.floriantoenjes.lonely.user.profile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private ProfileRepository profileRepository;

    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping()
    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    @PostMapping()
    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }

}
