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
            "insert into doctors (id,password,name,age,dept,sex,phone,fee,introduction) " +
                    "values (#{id},#{password},#{name},#{age},#{dept},#{sex},#{phone},#{fee},#{introduction})"
    )
    public int add(Doctor doctor);

    //删除医生数据
    //public int delete(Doctor doctor);

    //根据医生手机号查询数据

    @Select("select * from doctors where phone=#{doctorPhone}")

    public Doctor queryByPhone(String doctorPhone);

    //根据医生身份证号查询数据
    @Select("select * from doctors where id=#{doctorId}")
    public Doctor queryById(String doctorId);

    //根据所属科室查询医生信息
    @Select("select * from doctors where dept=#{doctorDept}")
    public List<Doctor> queryByDept(String doctorDept);

    //根据身份证号查询所在科室
    @Select("select dept from doctors where id=#{doctorId}")
    public String queryDeptById(String doctorId);

    //根据身份证号查询医生名称
    @Select("select name from doctors where id=#{doctorId}")
    public String queryNameById(String doctorId);

    //修改医生介绍信息
    @Update("update doctors set introduction = #{introduction} where id = #{id}")
    public int updateInfo(Doctor doctor);
}
