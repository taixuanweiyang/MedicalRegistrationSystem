package com.example.medicalregistrationsystem.mapper;
import com.example.medicalregistrationsystem.pojo.Perscription;
import com.example.medicalregistrationsystem.pojo.Registration;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RegistrationMapper {
    //查询所有挂号信息
    public List<Registration> queryAll();

    //添加挂号记录
    public int add(Registration registration);

    //根据患者身份证号查询数据
    public List<Registration> queryByPatientId(String patientId);

    //根据医生身份证号查询数据
    public List<Registration> queryByDoctorId(String doctorId);

    //使失效/取消挂号信息：
    public int CancelOrTimeout(String patientNumber);

    //修改挂号信息
//    public int updateInfo(Perscription perscription);
}
