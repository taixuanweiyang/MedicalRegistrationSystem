package com.example.medicalregistrationsystem.mapper;
import com.example.medicalregistrationsystem.pojo.MedicalRecord;
import com.example.medicalregistrationsystem.pojo.Prescription;
import com.example.medicalregistrationsystem.pojo.Registration;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RegistrationMapper {
    //查询所有挂号信息
    @Select("select registration.* from registration")
//    @Results(id="registrationMap",value={
//            @Result(id=true,column = "registration.patientNumber",property = "patientNumber"),
//            @Result(column = "registTime",property = "registTime"),
//            @Result(column = "registDept",property = "registDept"),
//            @Result(column = "reserveTime",property = "reserveTime"),
//            @Result(column = "timeRange",property = "timeRange"),
//            @Result(column = "medicalStatus",property = "medicalStatus"),
//            @Result(column = "doctorId",property = "doctorId"),
//            @Result(column = "doctors.name",property = "doctorName"),
//            @Result(column = "patientId",property = "patientId"),
//            @Result(column = "patients.name",property = "patientName"),
//    })
    public List<Registration> queryAll();

    //添加挂号数据
    @Insert(
            "insert into registration(registTime,reserveTime,timeRange,medicalStatus,registDept,doctorId,patientId)"+
                    "values (#{registTime},#{reserveTime},#{timeRange},#{medicalStatus},#{registDept},#{doctorId},#{patientId})"
    )
    public int add(Registration registration);

    //根据患者身份证号查询数据
    @Select("select registration.* from registration where patientId=#{patientId}")
//    @ResultMap("registrationMap")
    public List<Registration> queryByPatientId(String patientId);

    //根据医生身份证号查询数据
    @Select("select registration.* from registration where registration.doctorId=#{doctorId} and medicalStatus = 0 order by reserveTime")
//    @ResultMap("registrationMap")
    public List<Registration> queryByDoctorId(String doctorId);

    //使失效/取消挂号信息：
    @Update("update registration set medicalStatus=-1 where patientNumber=#{patientNumber} ")
    public int CancelOrTimeout(String patientNumber);

    //已经就诊
    @Update("update registration set medicalStatus=1 where patientNumber=#{patientNumber} ")
    public int FinishMedical(String patientNumber);

    //统计预约挂号数量
    @Select("select count(*) from registration where doctorId=#{doctorId} and reserveTime=#{data} and timeRange=#{timeRange}")
    public int CountRegistration(String doctorId,String data,boolean timeRange);

    @Select("select count(*) from registration where patientId = #{patientId} and doctorId=#{doctorId} and medicalStatus = 0")
    public int CountPatientRegistration(Registration registration);

    @Select("select count(*) from registration where patientId = #{patientId} and doctorId=#{doctorId} and medicalStatus = 0" +
            "and reserveTime = #{reserveTime} and timeRange = #{timeRange}")
    public int CheckRegistrationTime(MedicalRecord medicalRecord, String reserveTime, boolean timeRange);

    @Select("select patientId from registration where patientNumber = #{patientNumber}")
    public String QueryPatientIdByPatientNumber(String patientNumber);
    //修改挂号信息
//    public int updateInfo(Prescription prescription);
}
