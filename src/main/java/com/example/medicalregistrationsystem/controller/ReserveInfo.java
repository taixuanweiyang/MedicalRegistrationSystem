package com.example.medicalregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReserveInfo {
    @RequestMapping(value = "/reverse/info", method = RequestMethod.GET)
    public String reverseInfo(String doctorName, String date, boolean timeRange){

        return "";
    }
}
