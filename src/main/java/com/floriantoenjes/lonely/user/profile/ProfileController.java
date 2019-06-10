package com.floriantoenjes.lonely.user.profile;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.floriantoenjes.lonely.utils.AuthUtils.getUsernameFromAuth;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private ProfileRepository profileRepository;

    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    @PostMapping
    public Profile updateProfile(@RequestBody Profile profile) {
        String username = getUsernameFromAuth();

        Optional<Profile> existingProfile = profileRepository.findByUsername(username);
        existingProfile.ifPresent(profile1 -> profile.setId(profile1.getId()));
        profile.setUsername(username);

        return profileRepository.save(profile);
    }

    @GetMapping("/my-profile")
    public Optional<Profile> getProfile() {
        return profileRepository.findByUsername(getUsernameFromAuth());
    }

}
