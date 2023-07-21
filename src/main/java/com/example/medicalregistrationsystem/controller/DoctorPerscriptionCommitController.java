package com.example.medicalregistrationsystem.controller;

import com.example.medicalregistrationsystem.pojo.Perscription;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorPerscriptionCommitController {
    @RequestMapping(value = "/doctor/perscription/commit", method = RequestMethod.POST)
    public String doctorPerscriptionCommit(@RequestBody Perscription perscription) {

        return "";
    }
}
