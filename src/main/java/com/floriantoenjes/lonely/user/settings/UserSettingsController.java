package com.floriantoenjes.lonely.user.settings;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settings")
public class UserSettingsController {

    private UserSettingsRepository userSettingsRepository;

    public UserSettingsController(UserSettingsRepository userSettingsRepository) {
        this.userSettingsRepository = userSettingsRepository;
    }

    @GetMapping
    public List<UserSettings> getSettings() {
        return userSettingsRepository.findAll();
    }

    @PostMapping
    public UserSettings saveSettings(@RequestBody UserSettings userSettings) {
        return userSettingsRepository.save(userSettings);
    }

}
