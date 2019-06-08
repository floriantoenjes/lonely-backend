package com.floriantoenjes.lonely.user.settings;

import com.floriantoenjes.lonely.user.profile.Profile;
import com.floriantoenjes.lonely.user.profile.ProfileRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/settings")
public class UserSettingsController {

    private ProfileRepository profileRepository;

    private UserSettingsRepository userSettingsRepository;

    public UserSettingsController(ProfileRepository profileRepository, UserSettingsRepository userSettingsRepository) {
        this.profileRepository = profileRepository;
        this.userSettingsRepository = userSettingsRepository;
    }

    @GetMapping
    public List<UserSettings> getSettings() {
        return userSettingsRepository.findAll();
    }

    @PostMapping
    public UserSettings saveSettings(@RequestBody UserSettings userSettings) {
        Optional<Profile> profile = profileRepository.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());

        profile.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userSettings.setProfile(profile.get());

        return userSettingsRepository.save(userSettings);
    }

}
