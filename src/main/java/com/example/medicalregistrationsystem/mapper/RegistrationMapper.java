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
    public Registration queryByPatientId(String patientId);

    //根据医生身份证号查询数据
    @Select("select * from registration where doctorId=#{doctorId}")
    public Registration queryByDoctorId(String doctorId);

    //使失效/取消挂号信息：
    @Update("update registration set Medical_status=false where Patient_ID=#{patientId} and Doctor_ID=#{doctorId} and ")
    public int CancelOrTimeout(Registration registration);

    //修改gauhao信息
//    public int updateInfo(Perscription perscription);
}
