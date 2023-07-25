package com.example.medicalregistrationsystem.service;

import com.example.medicalregistrationsystem.pojo.*;

import java.util.List;

public interface PatientService {
    public PatientLogin patientLogin(String phone, String password);

    public boolean patientSignup(Patient patient);

    public boolean patientModify(Patient patient);

    public List<CaseHistory> getPatientCaseInfo(String patientID);

    public List<Prescription> getPatientPrescription(String patientID);

    public List<Registration> getPatientRegistration(String patientID);

    public boolean registrationCancel(String patientNumber);

    public boolean registrationCommit(Registration registration);

    public Patient getPatientById(String patientId);

    public boolean prescriptionPay(String patientNumber);
}
