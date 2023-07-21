package com.example.medicalregistrationsystem.mapper;

import com.example.medicalregistrationsystem.pojo.MedicalRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MedicalRecordMapper {
    //查询所有诊疗记录
    public List<MedicalRecord> queryAll();

    //添加诊疗记录
    public int add(MedicalRecord medicalRecord);


    //根据医生身份证号查询数据
    public MedicalRecord queryByDoctorId(String doctorId);

    //根据用户身份证号查询数据
    public MedicalRecord queryByPatientId(String doctorId);

    //删除删除诊疗记录
//    public int delete(MedicalRecord medicalRecord);
}
