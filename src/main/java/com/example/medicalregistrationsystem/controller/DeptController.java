package com.example.medicalregistrationsystem.controller;

import com.example.medicalregistrationsystem.pojo.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DeptController {
    private deptService dept;
    @RequestMapping(value = "/dept", method = RequestMethod.GET)
    public List<Department> dept(){


        return dept;
    }
}
