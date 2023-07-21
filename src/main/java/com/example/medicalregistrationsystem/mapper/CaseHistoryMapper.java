package com.example.medicalregistrationsystem.mapper;

import com.example.medicalregistrationsystem.pojo.CaseHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CaseHistoryMapper {
    //查询所有病历
    @Select("select * from `case_history`")
    public List<CaseHistory> queryAll();

    //添加病历
    @Insert(
            "insert into `case_history` (`Patient_number`,`Time`,`Patient_word`,`Deal_advice`,`Medical_result`,`Doctor_ID`)"+
            "'values (#{patientNumber},#{time},#{patientWord},#{dealAdvice},#{medicalResult},#{doctorId})"
    )
    public int add(CaseHistory caseHistory);

    //根据患者身份证号查询病历
    @Select(
            "select case_history.* from case_history,registration where registration.Patient_ID=#{patientId}" +
            "and case_history.Patient_number=registration.Patient_number"
    )
    public CaseHistory queryById(String patientId);
}
