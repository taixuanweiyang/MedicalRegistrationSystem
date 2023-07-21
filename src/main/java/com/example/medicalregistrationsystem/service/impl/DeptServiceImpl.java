package com.example.medicalregistrationsystem.service.impl;

import com.example.medicalregistrationsystem.mapper.DepartmentMapper;
import com.example.medicalregistrationsystem.pojo.Department;
import com.example.medicalregistrationsystem.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DepartmentMapper deptMapper;
    @Override
    public List<Department> listDept() {
        return deptMapper.queryAll();
    }
}
