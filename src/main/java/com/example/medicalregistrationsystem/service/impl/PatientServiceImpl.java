package com.example.medicalregistrationsystem.service.impl;

import com.example.medicalregistrationsystem.mapper.*;
import com.example.medicalregistrationsystem.pojo.*;
import com.example.medicalregistrationsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private PrescriptionMapper prescriptionMapper;
    @Autowired
    private CaseHistoryMapper caseHistoryMapper;
    @Autowired
    private PrescriptionMapper perscriptionMapper;
    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public PatientLogin patientLogin(String phone, String password) {
        Patient patient = patientMapper.queryByPhone(phone);
        PatientLogin patientLogin = new PatientLogin();
        patientLogin.setPatient(patientMapper.queryByPhone(phone));
        if (patient != null && !password.equals(patient.getPassword())) {
            patientLogin.setSuccess(false);
            patientLogin.setPatient(null);
        }
        return patientLogin;
    }

    @Override
    public boolean patientSignup(Patient patient) {
//        System.out.println(patient.getPhone());
        Patient PatientInfo = patientMapper.queryByPhone(patient.getPhone());

        if (PatientInfo != null) return false;
        patientMapper.add(patient);
        return true;
    }

    @Override
    public boolean patientModify(Patient patient) {
        int row = 0;
        try{
            row = patientMapper.updateInfo(patient);
        }
        catch (Exception e) {
            return false;
        }
        return row == 1;
    }


    @Override
    public List<CaseHistory> getPatientCaseInfo(String patientID) {
        return caseHistoryMapper.queryById(patientID);
    }


    @Override
    public List<Prescription> getPatientPrescription(String patientID) {
        return perscriptionMapper.queryById(patientID);
    }


    @Override
    public List<Registration> getPatientRegistration(String patientId) {
        return registrationMapper.queryByPatientId(patientId);
    }

    @Override
    public boolean registrationCancel(String patientNumber) {
        int row;
        try {
            row = registrationMapper.CancelOrTimeout(patientNumber);
        }
        catch (Exception e) {
            return false;
        }
        return row == 1;
    }

    @Override
    public boolean registrationCommit(Registration registration) {
//        registrationMapper.add(registration);
        try {
            String dept = doctorMapper.queryDeptById(registration.getDoctorId());
            if(!dept.equals(registration.getRegistDept())) {
                return false;
            }
            registrationMapper.add(registration);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }



    @Override
    public boolean prescriptionPay(String patientNumber) {
        return prescriptionMapper.updateInfo(patientNumber) == 1;
    }
}
