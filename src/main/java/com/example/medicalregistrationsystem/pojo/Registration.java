package com.example.medicalregistrationsystem.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {
    private String patientNumber;
    private String regisTime;
    private String regisDept;
    private String reservTime;
    private boolean timeRange;
    private int medicalStatus;
    private String doctorId;
    private String patientId;

}
