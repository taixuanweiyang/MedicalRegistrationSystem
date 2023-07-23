package com.example.medicalregistrationsystem.controller;

import com.example.medicalregistrationsystem.mapper.PatientMapper;
import com.example.medicalregistrationsystem.pojo.*;
import com.example.medicalregistrationsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    private PatientService patientService;
    @RequestMapping(value = "/patient/login", method = RequestMethod.GET)
    public PatientLogin login(String phone, String password) {

        return patientService.patientLogin(phone, password);
    }

    @RequestMapping(value = "/patient/signup", method = RequestMethod.POST)
    public boolean patientSignup(@RequestBody Patient patient) {
//        System.out.println(patient);
        return patientService.patientSignup(patient);
    }

    @RequestMapping(value = "/patient/modify", method = RequestMethod.POST)
    public boolean patientModify(@RequestBody Patient patient) {

        return patientService.patientModify(patient);
    }

    @RequestMapping(value = "/patient/case/info", method = RequestMethod.GET)
    public List<CaseHistory> patientCaseInfo(String patientId) {

        return patientService.getPatientCaseInfo(patientId);
    }

    @RequestMapping(value = "/patient/prescription/info", method = RequestMethod.GET)
    public List<Prescription> patientPrescriptionInfo(String patientId) {

        return patientService.getPatientPrescription(patientId);
    }

    @RequestMapping(value = "/patient/registration/info", method = RequestMethod.GET)
    public List<Registration> patientRegistrationInfo(String patientId) {

        return patientService.getPatientRegistration(patientId);
    }

    @RequestMapping(value = "/registration/cancel", method = RequestMethod.GET)
    public boolean registrationCancel(String patientNumber) {

        return patientService.registrationCancel(patientNumber);
    }

    @RequestMapping(value = "/registration/commit", method = RequestMethod.POST)
    public boolean registrationCommit(@RequestBody Registration registration) {

        return patientService.registrationCommit(registration);
    }

    @RequestMapping(value = "patient/prescription/pay", method = RequestMethod.GET)
    public boolean prescriptionPay(String patientNumber) {
        return patientService.prescriptionPay(patientNumber);
    }
}
