package com.example.medicalregistrationsystem.controller;

import com.example.medicalregistrationsystem.pojo.CaseHistory;
import org.apache.ibatis.annotations.Case;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorCaseCommitController {
    @RequestMapping(value = "/doctor/case/commit", method = RequestMethod.POST)
    public String doctorCaseCommit(@RequestBody CaseHistory caseHistory) {

        return "";
    }
}
