package com.floriantoenjes.lonely.meetup;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/meetup")
public class MeetupController {

    private MeetupRepository meetupRepository;

    public MeetupController(MeetupRepository meetupRepository) {
        this.meetupRepository = meetupRepository;
    }

    @GetMapping
    public List<Meetup> getMeetups() {
        return meetupRepository.findAll();
    }
}
