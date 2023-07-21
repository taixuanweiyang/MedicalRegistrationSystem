package com.example.medicalregistrationsystem.controller;

import com.example.medicalregistrationsystem.pojo.MedicalRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorMedicalRecordCommitController {
    @RequestMapping(value = "/doctor/medicalrecord/commit", method = RequestMethod.POST)
    public String doctorMedicalRecordCommit(@RequestBody MedicalRecord medicalRecord) {

        return "";
    }
}
