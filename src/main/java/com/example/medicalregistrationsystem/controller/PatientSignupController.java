package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PatientSignupController {
    @RequestMapping(value = "/patient/signup", method = RequestMethod.POST)
    public String signup(@RequestBody Patient patient) {


        return "";
    }
}
