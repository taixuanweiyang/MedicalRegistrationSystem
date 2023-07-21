package com.example.medicalregistrationsystem.mapper;

import com.example.medicalregistrationsystem.pojo.CaseHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CaseHistoryMapper {
    //查询所有病历
    public List<CaseHistory> queryAll();

    //添加病历
    public int add(CaseHistory caseHistory);

    //根据患者身份证号查询病历
    public CaseHistory queryById(String patientId);
}
