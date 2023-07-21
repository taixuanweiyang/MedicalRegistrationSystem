package com.example.medicalregistrationsystem.controller;

import com.example.medicalregistrationsystem.pojo.*;
import com.example.medicalregistrationsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public Doctor doctor(String dept){

        return doctorService.getDoctorByDept(dept);
    }

    @RequestMapping(value = "/doctor/login", method = RequestMethod.GET)
    public DoctorLogin doctorLogin(String phone, String password) {

        return doctorService.doctorLogin(phone, password);
    }

    @RequestMapping(value = "/doctor/signup", method = RequestMethod.POST)
    public boolean doctorSignup(@RequestBody Doctor doctor){

        return doctorService.doctorSignup(doctor);
    }

    @RequestMapping(value = "/doctor/case/allinfo", method = RequestMethod.GET)
    public List<CaseHistory> doctorCaseAllInfo() {

        return  doctorService.getAllCase();
    }

    @RequestMapping(value = "/doctor/case/commit", method = RequestMethod.POST)
    public boolean doctorCaseCommit(@RequestBody CaseHistory caseHistory) {

        return doctorService.caseCommit(caseHistory);
    }

    @RequestMapping(value = "/doctor/medicalrecord/commit", method = RequestMethod.POST)
    public boolean doctorMedicalRecordCommit(@RequestBody MedicalRecord medicalRecord) {

        return doctorService.medicalRecordCommit(medicalRecord);
    }

    @RequestMapping(value = "/doctor/perscription/allinfo", method = RequestMethod.GET)
    public List<Perscription> doctorPerscriptionAllInfo() {

        return doctorService.getAllPrescription();
    }

    @RequestMapping(value = "/doctor/perscription/commit", method = RequestMethod.POST)
    public boolean doctorPerscriptionCommit(@RequestBody Perscription perscription) {

        return doctorService.PrescriptionCommit(perscription);
    }

    @RequestMapping(value = "/doctor/registration/allinfo", method = RequestMethod.GET)
    public List<Registration> doctorRegistrationAllInfo() {

        return doctorService.getAllRegistration();
    }

    @RequestMapping(value = "/doctor/registration/specificinfo", method = RequestMethod.GET)
    public List<Registration> doctorRegistrationSpecificInfo(String doctorID) {

        return doctorService.getRegistrationByDoctorID(doctorID);
    }

    @RequestMapping(value = "/doctor/reserve/info", method = RequestMethod.GET)
    public int reserveInfo(String doctorName, String date, boolean timeRange){

        return doctorService.getReserveNumber(doctorName, date, timeRange);
    }
}
