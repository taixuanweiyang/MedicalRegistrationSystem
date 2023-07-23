package com.example.medicalregistrationsystem.service.impl;

import com.example.medicalregistrationsystem.mapper.*;
import com.example.medicalregistrationsystem.pojo.*;
import com.example.medicalregistrationsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private CaseHistoryMapper caseHistoryMapper;
    @Autowired
    private PrescriptionMapper perscriptionMapper;
    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Override
    public List<Doctor> getDoctorByDept(String dept) {

        return doctorMapper.queryByDept(dept);
    }

    @Override
    public DoctorLogin doctorLogin(String phone, String password) {
        Doctor doctor = doctorMapper.queryByPhone(phone);
        DoctorLogin doctorLogin = new DoctorLogin();
        doctorLogin.setDoctor(doctorMapper.queryByPhone(phone));
        if (doctor != null && !password.equals(doctor.getPassword())) {
            doctorLogin.setSuccess(false);
        }
        return doctorLogin;
    }

    @Override
    public boolean doctorSignup(Doctor doctor) {
//        System.out.println(doctor.getPhone());
        Doctor doctorInfo = doctorMapper.queryByPhone(doctor.getPhone());
        if (doctorInfo != null) return false;
        try {
            doctorMapper.add(doctor);
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }


    @Override
    public List<CaseHistory> getAllCase() {
        return caseHistoryMapper.queryAll();
    }

    @Override
    public boolean caseCommit(CaseHistory caseHistory) {
        caseHistoryMapper.add(caseHistory);
        registrationMapper.FinishMedical(caseHistory.getPatientNumber());
        return true;
    }


    @Override
    public boolean medicalRecordCommit(MedicalRecord medicalRecord) {
//        System.out.println(medicalRecord);
        try {
            medicalRecordMapper.add(medicalRecord);
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }


    @Override
    public List<Prescription> getAllPrescription() {
        return perscriptionMapper.queryAll();
    }

    @Override
    public boolean PrescriptionCommit(Prescription prescription) {
        try {
            perscriptionMapper.add(prescription);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public List<Registration> getAllRegistration() {
        return registrationMapper.queryAll();
    }

    @Override
    public List<Registration> getRegistrationByDoctorID(String doctorId) {
        return registrationMapper.queryByDoctorId(doctorId);
    }

    @Override
    public int getReserveNumber(String doctorId, String date, boolean timeRange) {
//        System.out.println(doctorId + date + timeRange);
        return registrationMapper.CountRegistration(doctorId, date, timeRange);
    }
}
