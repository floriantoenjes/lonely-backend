package com.floriantoenjes.lonely.user.profile;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        profile.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return profileRepository.save(profile);
    }

    @GetMapping("/{id}")
    public Optional<Profile> getProfile(@PathVariable String id) {
        return profileRepository.findById(id);
    }

}
