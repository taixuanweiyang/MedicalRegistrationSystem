package com.example.medicalregistrationsystem.mapper;
import com.example.medicalregistrationsystem.pojo.Prescription;
import com.example.medicalregistrationsystem.pojo.Registration;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RegistrationMapper {
    //查询所有挂号信息
    @Select("select * from registration")
    public List<Registration> queryAll();

    //添加挂号数据
    @Insert(
            "insert into registration(registTime,reserveTime,timeRange,medicalStatus,registDept,doctorId,patientId)"+
                    "values (#{registTime},#{reserveTime},#{timeRange},#{medicalStatus},#{registDept},#{doctorId},#{patientId})"
    )
    public int add(Registration registration);

    //根据患者身份证号查询数据
    @Select("select * from registration where patientId=#{patientId}")
    public List<Registration> queryByPatientId(String patientId);

    //根据医生身份证号查询数据
    @Select("select * from registration where doctorId=#{doctorId} and medicalStatus = 0")
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
    //修改挂号信息
//    public int updateInfo(Prescription prescription);
}
