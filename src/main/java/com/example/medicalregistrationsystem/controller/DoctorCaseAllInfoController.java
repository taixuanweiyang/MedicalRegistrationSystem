package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorCaseAllInfoController {
    @RequestMapping(value = "/doctor/case/allinfo", method = RequestMethod.GET)
    public String doctorCaseAllInfo() {

        return  " ";
    }
}
