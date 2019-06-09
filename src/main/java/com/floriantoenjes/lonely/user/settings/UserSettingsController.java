package com.floriantoenjes.lonely.user.settings;

import com.floriantoenjes.lonely.user.profile.Profile;
import com.floriantoenjes.lonely.user.profile.ProfileRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.floriantoenjes.lonely.utils.AuthUtils.getUsernameFromAuth;

@RestController
@RequestMapping("/settings")
public class UserSettingsController {

    private ProfileRepository profileRepository;

    private UserSettingsRepository userSettingsRepository;

    public UserSettingsController(ProfileRepository profileRepository, UserSettingsRepository userSettingsRepository) {
        this.profileRepository = profileRepository;
        this.userSettingsRepository = userSettingsRepository;
    }

    @PostMapping
    public UserSettings saveSettings(@RequestBody UserSettings userSettings) {
        Optional<Profile> profile = profileRepository.findByUsername(getUsernameFromAuth());
        profile.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userSettings.setProfile(profile.get());

        return userSettingsRepository.save(userSettings);
    }

    @GetMapping("my-settings")
    public UserSettings getSettingsByProfileId() {
        Optional<Profile> profile = profileRepository.findByUsername(getUsernameFromAuth());
        profile.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return userSettingsRepository.findByProfileId(profile.get().getId()).orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
    }

}

class ProfileNotFoundException extends RuntimeException {

    ProfileNotFoundException(String message) {
        super(message);
    }

}
