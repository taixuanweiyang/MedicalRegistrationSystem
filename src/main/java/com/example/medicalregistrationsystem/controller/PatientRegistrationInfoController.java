package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PatientRegistrationInfoController {
    @RequestMapping(value = "/patient/registration/info", method = RequestMethod.GET)
    public String patientRegistrationInfo(String patientID) {

        return "";
    }
}
