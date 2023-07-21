package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorRegistrationSpecificInfoController {
    @RequestMapping(value = "/doctor/registration/specificinfo", method = RequestMethod.GET)
    public String doctorRegistrationSpecificInfo(String doctorID) {

        return "";
    }
}
