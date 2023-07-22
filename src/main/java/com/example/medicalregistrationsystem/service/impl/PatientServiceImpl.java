package com.example.medicalregistrationsystem.service.impl;

import com.example.medicalregistrationsystem.mapper.CaseHistoryMapper;
import com.example.medicalregistrationsystem.mapper.PatientMapper;
import com.example.medicalregistrationsystem.mapper.PerscriptionMapper;
import com.example.medicalregistrationsystem.mapper.RegistrationMapper;
import com.example.medicalregistrationsystem.pojo.*;
import com.example.medicalregistrationsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientMapper patientMapper;
    @Override
    public PatientLogin patientLogin(String phone, String password) {
        Patient patient = patientMapper.queryByPhone(phone);
        PatientLogin patientLogin = new PatientLogin();
        patientLogin.setPatient(patientMapper.queryByPhone(phone));
        if (patient != null && !password.equals(patient.getPassword())) {
            patientLogin.setSuccess(false);
        }
        return patientLogin;
    }

    @Override
    public boolean patientSignup(Patient patient) {
        Patient PatientInfo = patientMapper.queryByPhone(patient.getPhone());
        if (PatientInfo != null) return false;
        patientMapper.add(patient);
        return true;
    }

    @Override
    public boolean patientModify(Patient patient) {
        return patientMapper.updateInfo(patient) > 0;
    }

    @Autowired
    private CaseHistoryMapper caseHistoryMapper;
    @Override
    public CaseHistory getPatientCaseInfo(String patientID) {
        return caseHistoryMapper.queryById(patientID);
    }

    @Autowired
    private PerscriptionMapper perscriptionMapper;
    @Override
    public List<Perscription> getPatientPrescription(String patientID) {
        return perscriptionMapper.queryById(patientID);
    }

    @Autowired
    private RegistrationMapper registrationMapper;
    @Override
    public List<Registration> getPatientRegistration(String patientID) {
        return registrationMapper.queryByPatientId(patientID);
    }

    @Override
    public boolean registrationCancel(String patientNumber) {
        return registrationMapper.CancelOrTimeout(patientNumber) > 0;
    }

    @Override
    public boolean registrationCommit(Registration registration) {
        registrationMapper.add(registration);
        return true;
    }
}
