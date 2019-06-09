package com.floriantoenjes.lonely.user.settings;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserSettingsRepository extends MongoRepository<UserSettings, String> {

    List<UserSettings> findAllByLonelyDateTimeIsBetween(LocalDateTime from, LocalDateTime till);

    Optional<UserSettings> findByProfileId(String profileId);

}
