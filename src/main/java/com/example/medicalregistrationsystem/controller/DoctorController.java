package com.example.medicalregistrationsystem.controller;

import com.example.medicalregistrationsystem.pojo.CaseHistory;
import com.example.medicalregistrationsystem.pojo.Doctor;
import com.example.medicalregistrationsystem.pojo.MedicalRecord;
import com.example.medicalregistrationsystem.pojo.Perscription;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorController {
    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public String doctor(String dept){

        return "";
    }

    @RequestMapping(value = "/doctor/login", method = RequestMethod.GET)
    public String doctorLogin(String phone, String password) {

        return "";
    }

    @RequestMapping(value = "/patient/signup", method = RequestMethod.POST)
    public boolean patientSignup(@RequestBody Doctor doctor){

        return false;
    }

    @RequestMapping(value = "/doctor/case/allinfo", method = RequestMethod.GET)
    public String doctorCaseAllInfo() {

        return  " ";
    }

    @RequestMapping(value = "/doctor/case/commit", method = RequestMethod.POST)
    public String doctorCaseCommit(@RequestBody CaseHistory caseHistory) {

        return "";
    }

    @RequestMapping(value = "/doctor/medicalrecord/commit", method = RequestMethod.POST)
    public String doctorMedicalRecordCommit(@RequestBody MedicalRecord medicalRecord) {

        return "";
    }

    @RequestMapping(value = "/doctor/perscription/allinfo", method = RequestMethod.GET)
    public String doctorPerscriptionAllInfo() {

        return "";
    }

    @RequestMapping(value = "/doctor/perscription/commit", method = RequestMethod.POST)
    public String doctorPerscriptionCommit(@RequestBody Perscription perscription) {

        return "";
    }

    @RequestMapping(value = "/doctor/registration/allinfo", method = RequestMethod.GET)
    public String doctorRegistrationAllInfo() {

        return "";
    }

    @RequestMapping(value = "/doctor/registration/specificinfo", method = RequestMethod.GET)
    public String doctorRegistrationSpecificInfo(String doctorID) {

        return "";
    }

    @RequestMapping(value = "/doctor/reserve/info", method = RequestMethod.GET)
    public String reserveInfo(String doctorName, String date, boolean timeRange){

        return "";
    }
}
