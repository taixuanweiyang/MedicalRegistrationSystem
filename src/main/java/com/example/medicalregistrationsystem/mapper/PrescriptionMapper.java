package com.example.medicalregistrationsystem.mapper;

import com.example.medicalregistrationsystem.pojo.Prescription;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PrescriptionMapper {
    //查询所有处方
    @Select("select * from prescription")
    public List<Prescription> queryAll();

    //添加处方数据
    @Insert(
            "insert into prescription (patientNumber,time,content,feeStatus,doctorId)"+
                    "values (#{patientNumber},#{time},#{content},#{feeStatus},#{doctorId})"
    )
    public int add(Prescription prescription);

    //根据患者身份证号查询数据
    @Select(
            "select * from prescription where patientNumber in" +
                    "(select patientNumber from registration where patientId=#{patientId})"
    )
    public List<Prescription> queryById(String patientId);

    //删除数据
//    public int delete(Prescription prescription);

    //修改处方信息
    @Update("update prescription set feeStatus = true where patientNumber=#{patientNumber}")
    public int updateInfo(String patientNumber);
}
