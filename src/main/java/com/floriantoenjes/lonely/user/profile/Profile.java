package com.floriantoenjes.lonely.user.profile;

import com.floriantoenjes.lonely.meetup.Meetup;
import lombok.Data;

import java.util.List;

@Data
public class Profile {
    private String id;

    private String userId;

    private String firstName;

    private String lastName;

    private String location;

    private Sex sex;

    private int age;

    private String pictureURL;

    private List<Meetup> meetups;

    public Profile(String id) {
        this.id = id;
    }
}

enum Sex {
    MALE,
    FEMALE
}
