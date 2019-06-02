package com.floriantoenjes.lonely.user.settings;

import com.floriantoenjes.lonely.user.profile.Profile;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Data
public class UserSettings {

    private String id;

    @DBRef
    private Profile profile;

    private LocalDateTime lonelyDateTime;

    private int radius;

    private int meetUpAgeFrom;

    private int meetUpAgeTo;

}
