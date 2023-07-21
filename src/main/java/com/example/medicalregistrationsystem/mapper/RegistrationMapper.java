package com.example.medicalregistrationsystem.mapper;
import com.example.medicalregistrationsystem.pojo.Perscription;
import com.example.medicalregistrationsystem.pojo.Registration;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RegistrationMapper {
    //查询所有处方
    public List<Registration> queryAll();

    //添加处方数据
    public int add(Registration registration);

    //根据患者身份证号查询数据
    public Registration queryByPatientId(String patientId);

    //根据患者身份证号查询数据
    public Registration queryByDoctorId(String doctorId);

    //使失效/取消挂号信息：
    public int CancelOrTimeout(Registration registration);

    //修改处方信息
//    public int updateInfo(Perscription perscription);
}
