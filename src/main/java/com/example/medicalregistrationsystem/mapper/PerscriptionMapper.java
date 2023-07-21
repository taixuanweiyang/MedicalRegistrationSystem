package com.example.medicalregistrationsystem.mapper;
import com.example.medicalregistrationsystem.pojo.Doctor;
import com.example.medicalregistrationsystem.pojo.Perscription;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PerscriptionMapper {
    //查询所有处方
    public List<Perscription> queryAll();

    //添加处方数据
    public int add(Perscription perscription);

    //根据患者身份证号查询数据
    public Perscription queryById(String patientId);

    //删除数据
//    public int delete(Perscription perscription);

    //修改处方信息
//    public int updateInfo(Perscription perscription);
}
