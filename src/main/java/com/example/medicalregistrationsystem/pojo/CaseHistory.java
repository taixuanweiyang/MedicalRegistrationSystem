package com.example.medicalregistrationsystem.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseHistory {
    private String patientNumber;
    private String time;
    private String patientWord;
    private String dealAdvice;
    private String medicalResult;
    private String doctorId;
    private String patientId;
}
