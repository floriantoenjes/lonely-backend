package com.floriantoenjes.lonely.user.settings;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface UserSettingsRepository extends MongoRepository<UserSettings, String> {

    List<UserSettings> findAllByLonelyDateTimeIsBetween(LocalDateTime from, LocalDateTime till);

}
