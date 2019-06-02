package com.floriantoenjes.lonely.user.settings;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSettings {

    private String id;

    private String userId;

    private LocalDateTime lonelyDateTime;

    private int radius;

    private int meetUpAgeFrom;

    private int meetUpAgeTo;

}
