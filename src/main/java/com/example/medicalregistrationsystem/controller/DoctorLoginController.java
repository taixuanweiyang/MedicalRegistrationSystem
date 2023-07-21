package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorLoginController {
    @RequestMapping(value = "/doctor/login", method = RequestMethod.GET)
    public String doctorLogin(String phone, String password) {

        return "";
    }
}
