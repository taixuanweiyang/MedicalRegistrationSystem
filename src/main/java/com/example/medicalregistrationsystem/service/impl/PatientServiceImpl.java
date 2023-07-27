package com.example.medicalregistrationsystem.service.impl;

import com.example.medicalregistrationsystem.mapper.*;
import com.example.medicalregistrationsystem.pojo.*;
import com.example.medicalregistrationsystem.service.PatientService;
import org.apache.commons.codec.digest.DigestUtils;
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
    private RegistrationMapper registrationMapper;
    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public PatientLogin patientLogin(String phone, String password) {
        password = DigestUtils.md2Hex(password);
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
        patient.setPassword(DigestUtils.md2Hex(patient.getPassword()));
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
        return prescriptionMapper.queryById(patientID);
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
//            String dept = doctorMapper.queryDeptById(registration.getDoctorId());
//            if(!dept.equals(registration.getRegistDept())) {
//                return false;
//            }
            int count = registrationMapper.CountPatientRegistration(registration);
            System.out.println(count);
            if(count > 0) {
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
    public Patient getPatientById(String patientId) {
        Patient result = patientMapper.queryById(patientId);
        System.out.println(result);
        return result;
    }

    @Override
    public boolean prescriptionPay(String patientNumber) {
        return prescriptionMapper.updateInfo(patientNumber) == 1;
    }
}
