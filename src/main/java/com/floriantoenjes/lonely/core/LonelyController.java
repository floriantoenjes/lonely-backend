package com.floriantoenjes.lonely.core;

import com.floriantoenjes.lonely.user.profile.Profile;
import com.floriantoenjes.lonely.user.profile.ProfileRepository;
import com.floriantoenjes.lonely.user.settings.UserSettings;
import com.floriantoenjes.lonely.user.settings.UserSettingsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/core")
public class LonelyController {

    private ProfileRepository profileRepository;

    private UserSettingsRepository userSettingsRepository;

    public LonelyController(ProfileRepository profileRepository, UserSettingsRepository userSettingsRepository) {
        this.profileRepository = profileRepository;
        this.userSettingsRepository = userSettingsRepository;
    }

    @GetMapping("/lonely-people")
    public List<Map<String, Object>> getLonelyPeople() {
        List<Map<String, Object>> partialProfileList = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        List<UserSettings> userSettings = userSettingsRepository.findAllByLonelyDateTimeIsBetween(
                now.withHour(0).withMinute(0).withSecond(0), now.withHour(23).withMinute(59).withSecond(59)
        );

        Iterable<Profile> lonelyProfiles = profileRepository.findAllById(
                userSettings.stream().filter(settings -> settings.getProfile() != null).map(settings -> settings.getProfile().getId()).collect(Collectors.toList())
        );

        lonelyProfiles.forEach(profile -> {
            Map<String, Object> partialProfileMap = new HashMap<>();

            partialProfileMap.put("firstName", profile.getFirstName());
            partialProfileMap.put("description", profile.getDescription());
            partialProfileMap.put("pictureURL", profile.getPictureURL());
            partialProfileMap.put("sex", profile.getSex());
            partialProfileMap.put("location", profile.getLocation());

            partialProfileList.add(partialProfileMap);
        });

        return partialProfileList;
    }
}
