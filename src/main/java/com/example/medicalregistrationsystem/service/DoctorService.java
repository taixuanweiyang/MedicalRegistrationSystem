package com.example.medicalregistrationsystem.service;

import com.example.medicalregistrationsystem.pojo.*;

import java.util.List;

public interface DoctorService {
    public List<Doctor> getDoctorByDept(String dept);

    public DoctorLogin doctorLogin(String phone, String password);

    public boolean doctorSignup(Doctor doctor);

    public List<CaseHistory> getAllCase();

    public boolean caseCommit(CaseHistory caseHistory);

    public boolean medicalRecordCommit(MedicalRecord medicalRecord);

    public List<Prescription> getAllPrescription();

    public boolean PrescriptionCommit(Prescription prescription);

    public List<Registration> getAllRegistration();

    public List<Registration> getRegistrationByDoctorID(String doctorID);

    public int getReserveNumber(String doctorId, String date, boolean timeRange);
}
