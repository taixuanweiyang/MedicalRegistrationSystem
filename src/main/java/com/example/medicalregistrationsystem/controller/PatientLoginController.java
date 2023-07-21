package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PatientLoginController {
    @RequestMapping(value = "/patient/login", method = RequestMethod.GET)
    public String login(String phone, String password) {

        return "";
    }
}
