package com.example.medicalregistrationsystem.service;

import com.example.medicalregistrationsystem.mapper.PerscriptionMapper;
import com.example.medicalregistrationsystem.pojo.*;

import java.util.List;

public interface DoctorService {
    public Doctor getDoctorByDept(String dept);

    public DoctorLogin doctorLogin(String phone, String password);

    public boolean doctorSignup(Doctor doctor);

    public List<CaseHistory> getAllCase();

    public boolean caseCommit(CaseHistory caseHistory);

    public boolean medicalRecordCommit(MedicalRecord medicalRecord);

    public List<Perscription> getAllPrescription();

    public boolean PrescriptionCommit(Perscription perscription);

    public List<Registration> getAllRegistration();

    public List<Registration> getRegistrationByDoctorID(String doctorID);

    public int getReserveNumber(String doctorName, String date, boolean timeRange);
}
