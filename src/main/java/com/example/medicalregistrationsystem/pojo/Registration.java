package com.example.medicalregistrationsystem.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {
    private String patientNumber;
    private String registTime;
    private String registDept;
    private String reserveTime;
    private boolean timeRange;
    private int medicalStatus;
    private String doctorId;
    private String doctorName;
    private String patientId;
    private String patientName;
}
