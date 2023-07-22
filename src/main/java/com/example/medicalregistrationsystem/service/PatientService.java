package com.example.medicalregistrationsystem.service;

import com.example.medicalregistrationsystem.pojo.*;

import java.util.List;

public interface PatientService {
    public PatientLogin patientLogin(String phone, String password);

    public boolean patientSignup(Patient patient);

    public boolean patientModify(Patient patient);

    public CaseHistory getPatientCaseInfo(String patientID);

    public List<Prescription> getPatientPrescription(String patientID);

    public List<Registration> getPatientRegistration(String patientID);

    public boolean registrationCancel(String patientNumber);

    public boolean registrationCommit(Registration registration);

    public int getReserveNumber(String doctorId, String date, boolean timeRange);

    public boolean prescriptionPay(String patientNumber);
}
