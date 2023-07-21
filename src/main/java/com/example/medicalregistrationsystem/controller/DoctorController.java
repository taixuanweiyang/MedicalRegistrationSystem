package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorController {
    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public String doctor(String dept){

        return "";
    }
}
