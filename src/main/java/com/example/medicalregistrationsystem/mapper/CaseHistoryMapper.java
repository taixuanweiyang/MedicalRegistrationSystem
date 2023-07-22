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
            "insert into `case_history` (`patientNumber`,`Time`,`patientWord`,`dealAdvice`,`medicalResult`,`doctorId`)"+
            "values (#{patientNumber},#{time},#{patientWord},#{dealAdvice},#{medicalResult},#{doctorId})"
    )
    public int add(CaseHistory caseHistory);

    //根据患者身份证号查询病历
    @Select(
            "select case_history.* from case_history,registration where registration.patientId=#{patientId}" +
            "and case_history.patientNumber=registration.patientNumber"
    )
    public CaseHistory queryById(String patientId);
}
