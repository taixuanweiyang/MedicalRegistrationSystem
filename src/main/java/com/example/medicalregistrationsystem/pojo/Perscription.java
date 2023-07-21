package com.example.medicalregistrationsystem.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perscription {
    private String patientNumber;
    private String time;
    private String contenet;
    private boolean feeStatus;
    private String doctorId;
}
