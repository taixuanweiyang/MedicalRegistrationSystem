package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PatientPerscriptionInfoController {
    @RequestMapping(value = "/patient/perscription/info", method = RequestMethod.GET)
    public String patientPerscriptionInfo(String patientID) {


        return "";
    }
}
