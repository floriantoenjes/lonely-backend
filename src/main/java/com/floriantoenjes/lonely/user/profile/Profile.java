package com.floriantoenjes.lonely.user.profile;

import com.floriantoenjes.lonely.location.Location;
import com.floriantoenjes.lonely.meetup.Meetup;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Profile {
    private String id;

    private String userId;

    private String firstName;

    private String lastName;

    private Location location;

    private Sex sex;

    private int age;

    private String pictureURL;

    private String description;

    @DBRef
    private List<Meetup> meetups;
}

enum Sex {
    MALE,
    FEMALE
}
