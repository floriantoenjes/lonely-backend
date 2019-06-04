package com.floriantoenjes.lonely.meetup;

import com.floriantoenjes.lonely.location.Location;
import com.floriantoenjes.lonely.user.profile.Profile;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Meetup {
    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime localDateTime;

    private String title;

    private String description;

    private Location location;

    @DBRef
    private List<Profile> attendees;
}
