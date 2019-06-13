package com.floriantoenjes.lonely.user.profile;

import com.floriantoenjes.lonely.location.Location;
import com.floriantoenjes.lonely.meetup.Meetup;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class Profile {
    private String id;

    @Indexed(unique = true)
    private String username;

    private String firstName;

    private String lastName;

    private String city;

    private Location location;

    private Sex sex;

    private LocalDate birthDate;

    private String pictureURL;

    private String description;

    @DBRef
    private List<Meetup> meetups;
}

enum Sex {
    MALE,
    FEMALE
}
