package com.floriantoenjes.lonely.user.profile;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    Optional<Profile> findByUsername(String username);

    List<Profile> findAllByUsernameIn(List<String> usernames);
}
