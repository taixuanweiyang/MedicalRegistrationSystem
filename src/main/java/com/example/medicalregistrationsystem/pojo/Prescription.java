package com.example.medicalregistrationsystem.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    private String patientNumber;
    private String content;
    private String time;
    private boolean feeStatus;
    private String doctorId;
    private String patientId;
}
