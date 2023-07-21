package com.example.medicalregistrationsystem.service.impl;

import com.example.medicalregistrationsystem.mapper.CaseHistoryMapper;
import com.example.medicalregistrationsystem.mapper.DoctorMapper;
import com.example.medicalregistrationsystem.pojo.CaseHistory;
import com.example.medicalregistrationsystem.pojo.Doctor;
import com.example.medicalregistrationsystem.pojo.DoctorLogin;
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
    public List<CaseHistory> getCaseAllInfo() {
        return caseHistoryMapper.queryAll();
    }

    @Override
    public boolean caseCommit(CaseHistory caseHistory) {

        return false;
    }

}
