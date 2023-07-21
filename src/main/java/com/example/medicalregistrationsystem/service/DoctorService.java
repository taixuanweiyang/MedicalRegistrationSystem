package com.example.medicalregistrationsystem.service;

import com.example.medicalregistrationsystem.pojo.CaseHistory;
import com.example.medicalregistrationsystem.pojo.Doctor;
import com.example.medicalregistrationsystem.pojo.DoctorLogin;

import java.util.List;

public interface DoctorService {
    public Doctor getDoctorByDept(String dept);

    public DoctorLogin doctorLogin(String phone, String password);

    public boolean doctorSignup(Doctor doctor);

    public List<CaseHistory> getCaseAllInfo();

    public boolean caseCommit(CaseHistory caseHistory);
}
