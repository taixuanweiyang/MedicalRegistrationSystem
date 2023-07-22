package com.example.medicalregistrationsystem.mapper;


import com.example.medicalregistrationsystem.pojo.Doctor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DoctorMapper {
    //查询所有医生
    @Select("select * from doctors")
    public List<Doctor> queryAll();
    //添加医生数据
    @Insert(
            "insert into doctors (ID,Password,Name,Age,Dept,Sex,Phone,Fee,Introduction)"+
                    "'values (#{id},#{password},#{name},#{age},#{dept},#{sex},#{phone},#{fee},#{introductio})"
    )
    public int add(Doctor doctor);

    //删除医生数据
    //public int delete(Doctor doctor);

    //根据医生手机号查询数据

    @Select("select * from doctors where Phone=#{doctorPhone}")

    public Doctor queryByPhone(String doctorPhone);

    //根据医生身份证号查询数据
    @Select("select * from doctors where ID=#{doctorId}")
    public Doctor queryById(String doctorId);

    //根据所属科室查询医生信息
    @Select("select * from doctors where Dept=#{doctorDept}")
    public Doctor queryByDept(String doctorDept);
    //修改医生介绍信息
    @Update("update doctors set Introduction = #{introduction} wehere ID = #{id}")
    public int updateInfo(Doctor patient);
}
