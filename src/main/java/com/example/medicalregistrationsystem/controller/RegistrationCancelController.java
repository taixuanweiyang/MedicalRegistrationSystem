package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationCancelController {
    @RequestMapping(value = "/registration/cancel", method = RequestMethod.GET)
    public String registrationCancel(String patientNumber) {

        return "";
    }
}
