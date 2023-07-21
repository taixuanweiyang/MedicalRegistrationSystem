package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationCommitController {
    @RequestMapping(value = "/registration/commit", method = RequestMethod.POST)
    public String registrationCommit(@RequestBody Registration registration) {

        return "";
    }
}
