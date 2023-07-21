package com.example.medicalregistrationsystem.mapper;


import com.example.medicalregistrationsystem.pojo.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DoctorMapper {
    //查询所有医生
    public List<Doctor> queryAll();
    //添加医生数据
    public int add(Doctor doctor);

    //删除医生数据
    public int delete(Doctor doctor);

    //根据医生手机号查询数据
    public Doctor queryByPhone(String doctorPhone);

    //根据医生身份证号查询数据
    public Doctor queryById(String doctorId);

    //根据所属科室查询医生信息
    public Doctor queryByDept(String doctorDept);
    //修改医生信息
    public int updateInfo(Doctor patient);
}
