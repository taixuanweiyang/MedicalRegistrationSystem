package com.example.medicalregistrationsystem.controller;

import com.example.medicalregistrationsystem.pojo.Department;
import com.example.medicalregistrationsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DepartmentService dept;
    @RequestMapping(value = "/dept", method = RequestMethod.GET)
    public List<Department> dept(){
        return dept.listDept();
    }
}
