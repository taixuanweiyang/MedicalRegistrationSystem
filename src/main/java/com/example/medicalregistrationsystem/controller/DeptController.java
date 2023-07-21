package com.example.medicalregistrationsystem.controller;

import com.example.medicalregistrationsystem.pojo.Department;
import com.example.medicalregistrationsystem.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DeptController {
    private DepartmentService dept;
    @RequestMapping(value = "/dept", method = RequestMethod.GET)
    public List<Department> dept(){
        return dept.listDept();
    }
}
