package com.floriantoenjes.lonely.user.profile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping()
    public Profile getProfile() {
        return new Profile("1");
    }

    @PostMapping()
    public Profile updateProfile(Profile profile) {
        return profile;
    }

}
