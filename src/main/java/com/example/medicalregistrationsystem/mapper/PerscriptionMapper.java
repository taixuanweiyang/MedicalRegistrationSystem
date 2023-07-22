package com.example.medicalregistrationsystem.mapper;

import com.example.medicalregistrationsystem.pojo.Perscription;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PerscriptionMapper {
    //查询所有处方
    @Select("select * from perscription")
    public List<Perscription> queryAll();

    //添加处方数据
    @Insert(
            "insert into Perscription (Patient_number,Time,Content,Fee_status,Doctor_ID)"+
                    "values (#{patientNumber},#{time},#{content},#{feeStatus},#{doctorId})"
    )
    public int add(Perscription perscription);

    //根据患者身份证号查询数据
    @Select(
            "select * from perscription where Patient_number in" +
                    "(select Patient_number from registration where Patient_ID=patientid)"
    )
    public List<Perscription> queryById(String patientId);

    //删除数据
//    public int delete(Perscription perscription);

    //修改处方信息
//    public int updateInfo(Perscription perscription);
}
