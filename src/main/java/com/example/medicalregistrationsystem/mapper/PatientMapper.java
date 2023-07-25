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
            "insert into patients (id,password,name,age,sex,phone) values (#{id},#{password},#{name},#{age},#{sex},#{phone})"
    )
    public int add(Patient patient);

    //根据患者手机号查询数据
    @Select("select * from patients where phone=#{patientPhone}")
    public Patient queryByPhone(String patientPhone);

    //根据患者身份证号查询数据
    @Select("select * from patients where id = #{patientId}")
    public Patient queryById(String patientId);

    //根据患者身份证号查询数据
    @Select("select name from patients where id = #{patientId}")
    public String queryNameById(String patientId);

    //修改患者信息
    @Update("update patients set name=#{name},age=#{age},sex=#{sex},phone=#{phone}, password = #{password} where id = #{id}")
    public int updateInfo(Patient patient);
}
