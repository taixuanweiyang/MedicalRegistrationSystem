package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorPerscriptionAllInfoController {
    @RequestMapping(value = "/doctor/perscription/allinfo", method = RequestMethod.GET)
    public String doctorPerscriptionAllInfo() {

        return "";
    }
}
