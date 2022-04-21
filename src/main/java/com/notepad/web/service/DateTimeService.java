package com.notepad.web.service;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class DateTimeService {

    public ZonedDateTime getTime() {
        return ZonedDateTime.now();
    }
}
