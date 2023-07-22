package com.example.medicalregistrationsystem.mapper;

import com.example.medicalregistrationsystem.pojo.Patient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatientMapper {
    //查询所有患者
    @Select("select * from patient")
    public List<Patient> queryAll();

    //添加患者数据
    @Insert(
            "insert into patient (ID,Password,Name,Age,Sex,Phone)"+
                    "values (#{id},#{password},#{name},#{age},#{sex},#{phone})"
    )
    public int add(Patient patient);

    //根据患者手机号查询数据
    @Select("select * from patients where Phone=#{patientPhone}")
    public Patient queryByPhone(String patientPhone);

    //根据患者身份证号查询数据
    @Select("select * from patients where ID=#{patientId}")
    public Patient queryById(String patientId);

    //修改患者信息
    @Update("update patients set Name=#{name},Age=#{age},Sex=#{sex},Phone=#{phone} wehere ID = #{id}")
    public int updateInfo(Patient patient);
}
