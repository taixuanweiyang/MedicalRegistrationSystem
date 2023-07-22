package com.example.medicalregistrationsystem.mapper;
import com.example.medicalregistrationsystem.pojo.Perscription;
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
            "insert into registration (Patient_number,Reg_Time,Res_Time,Time_Range,Medical_status,Reg_Dept,Doctor_ID,Patient_ID)"+
                    "values (#{patientNumber},#{regTime},#{resTime},#{timeRange},#{medicalStatus},#{regDept},#{doctorId},#{patientId})"
    )
    public int add(Registration registration);

    //根据患者身份证号查询数据
    @Select("select * from registration where patientId=#{patientId}")
    public List<Registration> queryByPatientId(String patientId);

    //根据医生身份证号查询数据
    @Select("select * from registration where doctorId=#{doctorId}")
    public List<Registration> queryByDoctorId(String doctorId);

    //使失效/取消挂号信息：
    @Update("update registration set Medical_status=false where Patient_number=#{patientNumber} ")
    public int CancelOrTimeout(String patientNumber);

    //统计预约挂号数量
    @Select("select count(*) from registration where Doctor_ID=#{doctorId} and Date=#{data} and Time_range=#{timeRange}")
    public int CountRegistration(String doctorId,String data,boolean timeRange);
    //修改挂号信息
//    public int updateInfo(Perscription perscription);
}
