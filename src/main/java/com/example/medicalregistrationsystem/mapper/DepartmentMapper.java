package com.example.medicalregistrationsystem.mapper;

import com.example.medicalregistrationsystem.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {
    //查询所有科室
    public List<Department> queryAll();

    //添加科室数据
    public int add(Department department);

    //删除科室
    public int delete(Department department);

    //修改信息
    public int updateInfo(Department department);
}
