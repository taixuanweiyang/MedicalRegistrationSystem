package com.example.medicalregistrationsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientLogin {
    private boolean success;
    private Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
        success = (this.patient != null);
    }
}
