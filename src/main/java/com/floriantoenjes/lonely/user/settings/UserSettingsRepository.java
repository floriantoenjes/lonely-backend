package com.floriantoenjes.lonely.user.settings;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSettingsRepository extends MongoRepository<UserSettings, String> {
}
