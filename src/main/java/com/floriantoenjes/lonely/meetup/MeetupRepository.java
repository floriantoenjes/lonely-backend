package com.floriantoenjes.lonely.meetup;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeetupRepository extends MongoRepository<Meetup, String> {
}
