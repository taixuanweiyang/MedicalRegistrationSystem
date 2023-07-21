package com.example.medicalregistrationsystem.controller;

import com.example.medicalregistrationsystem.pojo.Patient;
import com.example.medicalregistrationsystem.pojo.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
    @RequestMapping(value = "/patient/login", method = RequestMethod.GET)
    public String login(String phone, String password) {

        return "";
    }

    @RequestMapping(value = "/patient/signup", method = RequestMethod.POST)
    public boolean patientSignup(@RequestBody Patient patient) {


        return false;
    }

    @RequestMapping(value = "/patient/modify", method = RequestMethod.POST)
    public String patientModify(@RequestBody Patient patient) {

        return "";
    }

    @RequestMapping(value = "/patient/case/info", method = RequestMethod.GET)
    public String patientCaseInfo(String patientID) {

        return "";
    }

    @RequestMapping(value = "/patient/perscription/info", method = RequestMethod.GET)
    public String patientPerscriptionInfo(String patientID) {


        return "";
    }

    @RequestMapping(value = "/patient/registration/info", method = RequestMethod.GET)
    public String patientRegistrationInfo(String patientID) {

        return "";
    }

    @RequestMapping(value = "/registration/cancel", method = RequestMethod.GET)
    public String registrationCancel(String patientNumber) {

        return "";
    }

    @RequestMapping(value = "/registration/commit", method = RequestMethod.POST)
    public String registrationCommit(@RequestBody Registration registration) {

        return "";
    }
}
