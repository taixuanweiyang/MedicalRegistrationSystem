package com.example.medicalregistrationsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorLogin {
    private boolean success;
    private Doctor doctor;

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        success = (this.doctor != null);
    }
}

