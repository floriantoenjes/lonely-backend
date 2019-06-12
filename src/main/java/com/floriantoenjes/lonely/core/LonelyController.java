package com.floriantoenjes.lonely.core;

import com.floriantoenjes.lonely.location.Location;
import com.floriantoenjes.lonely.user.profile.Profile;
import com.floriantoenjes.lonely.user.profile.ProfileNotFoundException;
import com.floriantoenjes.lonely.user.profile.ProfileRepository;
import com.floriantoenjes.lonely.user.settings.UserSettings;
import com.floriantoenjes.lonely.user.settings.UserSettingsNotFoundException;
import com.floriantoenjes.lonely.user.settings.UserSettingsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.floriantoenjes.lonely.utils.AuthUtils.getUsernameFromAuth;

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

        Profile signedInUserProfile = this.profileRepository.findByUsername(getUsernameFromAuth())
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

        UserSettings signedInUserSettings = this.userSettingsRepository
                .findByProfileId(signedInUserProfile.getId())
                .orElseThrow(() -> new UserSettingsNotFoundException("User settings not found"));

        LocalDateTime now = LocalDateTime.now();
        List<UserSettings> lonelyUserSettings = userSettingsRepository.findAllByLonelyDateTimeIsBetween(
                now.withHour(0).withMinute(0).withSecond(0), now.withHour(23).withMinute(59).withSecond(59)
        );

        List<String> userIds = lonelyUserSettings.stream()
                .map(settings -> settings.getProfile().getId()).collect(Collectors.toList());
        Iterable<Profile> lonelyProfiles = profileRepository.findAllById(userIds);

        Location signedInUserLocation = signedInUserProfile.getLocation();
        float signedInUserLat = (float) signedInUserLocation.getLatitude();
        float signedInUserLng = (float) signedInUserLocation.getLongitude();

        List<Profile> lonelyProfilesInRange = StreamSupport.stream(lonelyProfiles.spliterator(), false)
                .filter(profile -> {

                    Location profileLocation = profile.getLocation();
                    float profileLat = (float) profileLocation.getLatitude();
                    float profileLng = (float) profileLocation.getLongitude();

                    LocalDate from = LocalDate.now().minusYears(signedInUserSettings.getMeetUpAgeTo());
                    LocalDate to = LocalDate.now().minusYears(signedInUserSettings.getMeetUpAgeFrom());

                    return isAgeInRange(profile.getBirthDate(), from, to)
                            && isInRadius(signedInUserLat, signedInUserLng,
                            profileLat, profileLng, signedInUserSettings.getRadius());

                }).collect(Collectors.toList());

        lonelyProfilesInRange.forEach(profile -> {
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

    private boolean isAgeInRange(LocalDate date, LocalDate from, LocalDate to) {
        return date.isAfter(from) && date.isBefore(to);
    }

    private boolean isInRadius(float lat1, float lng1, float lat2, float lng2, int radius) {
        return distFrom(lat1, lng1, lat2, lng2) / 1000.0 <= radius;
    }

    private static float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6_371_000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);

        return dist;
    }
}
