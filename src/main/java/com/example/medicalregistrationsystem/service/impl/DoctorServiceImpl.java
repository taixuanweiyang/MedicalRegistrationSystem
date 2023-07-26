package com.example.medicalregistrationsystem.service.impl;

import com.example.medicalregistrationsystem.mapper.*;
import com.example.medicalregistrationsystem.pojo.*;
import com.example.medicalregistrationsystem.service.DoctorService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
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
    private PrescriptionMapper prescriptionMapper;
    @Autowired
    private MedicalRecordMapper medicalRecordMapper;
    @Autowired
    private PatientMapper patientMapper;

    @Override
    public List<Doctor> getDoctorByDept(String dept) {

        return doctorMapper.queryByDept(dept);
    }

    @Override
    public DoctorLogin doctorLogin(String phone, String password) {
        password = DigestUtils.md2Hex(password);
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
        doctor.setPassword(DigestUtils.md2Hex(doctor.getPassword()));
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
        List<CaseHistory> result = caseHistoryMapper.queryAll();
        for(CaseHistory caseHistory : result) {
            String patientId = registrationMapper.QueryPatientIdByPatientNumber(caseHistory.getPatientNumber());
            caseHistory.setPatientId(patientId);
        }
        return result;
    }

    @Override
    public boolean caseCommit(CaseHistory caseHistory) {
        caseHistoryMapper.add(caseHistory);
        int status = registrationMapper.FinishMedical(caseHistory.getPatientNumber());

        return status == 1;
    }


    @Override
    public boolean medicalRecordCommit(MedicalRecord medicalRecord) {
//        System.out.println(medicalRecord);
        String time = medicalRecord.getTime();
        String day = time.substring(0, 10);
        int hour = Integer.parseInt(time.substring(11, 13));

        try {
            if(registrationMapper.CheckRegistrationTime(medicalRecord.getDoctorId(), medicalRecord.getPatientId(), day, hour >= 12) == 0) {
                System.out.println("no registration");
                return false;
            }
            medicalRecordMapper.add(medicalRecord);
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }


    @Override
    public List<Prescription> getAllPrescription() {
        List<Prescription> result = prescriptionMapper.queryAll();
        for(Prescription prescription : result) {
            String patientId = registrationMapper.QueryPatientIdByPatientNumber(prescription.getPatientNumber());
            prescription.setPatientId(patientId);
        }

        return result;
    }

    @Override
    public boolean PrescriptionCommit(Prescription prescription) {
        try {
            prescriptionMapper.add(prescription);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public List<Registration> getAllRegistration() {
        List<Registration> result = registrationMapper.queryAll();

        for (Registration registration : result) {
            registration.setDoctorName(doctorMapper.queryNameById(registration.getDoctorId()));
            registration.setPatientName(patientMapper.queryNameById(registration.getPatientId()));
        }
//        System.out.println(result);
        return result;
    }

    @Override
    public List<Registration> getRegistrationByDoctorID(String doctorId) {
        List<Registration> result = registrationMapper.queryByDoctorId(doctorId);
        for (Registration registration : result) {
            registration.setDoctorName(doctorMapper.queryNameById(registration.getDoctorId()));
            registration.setPatientName(patientMapper.queryNameById(registration.getPatientId()));
        }
        return result;
    }

    @Override
    public int getReserveNumber(String doctorId, String date, boolean timeRange) {
//        System.out.println(doctorId + date + timeRange);
        return registrationMapper.CountRegistration(doctorId, date, timeRange);
    }

    @Override
    public Doctor getDoctorById(String doctorId) {
        return doctorMapper.queryById(doctorId);
    }
}
