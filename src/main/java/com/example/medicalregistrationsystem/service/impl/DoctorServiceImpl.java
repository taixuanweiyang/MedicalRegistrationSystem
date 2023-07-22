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
    @Override
    public Doctor getDoctorByDept(String dept) {

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
        Doctor doctorInfo = doctorMapper.queryByPhone(doctor.getPhone());
        if (doctorInfo != null) return false;
        doctorMapper.add(doctor);
        return true;
    }

    @Autowired
    private CaseHistoryMapper caseHistoryMapper;
    @Override
    public List<CaseHistory> getAllCase() {
        return caseHistoryMapper.queryAll();
    }

    @Override
    public boolean caseCommit(CaseHistory caseHistory) {
        caseHistoryMapper.add(caseHistory);
        return true;
    }

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;
    @Override
    public boolean medicalRecordCommit(MedicalRecord medicalRecord) {
        medicalRecordMapper.add(medicalRecord);
        return true;
    }

    @Autowired
    private PerscriptionMapper perscriptionMapper;
    @Override
    public List<Perscription> getAllPrescription() {
        return perscriptionMapper.queryAll();
    }

    @Override
    public boolean PrescriptionCommit(Perscription perscription) {
        perscriptionMapper.add(perscription);
        return true;
    }

    @Autowired
    private RegistrationMapper registrationMapper;
    @Override
    public List<Registration> getAllRegistration() {
        return registrationMapper.queryAll();
    }

    @Override
    public List<Registration> getRegistrationByDoctorID(String doctorID) {
        return registrationMapper.queryByDoctorId(doctorID);
    }

    @Override
    public int getReserveNumber(String doctorID, String date, boolean timeRange) {
        return registrationMapper.CountRegistration(doctorID, date, timeRange);
    }

}
