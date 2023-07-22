package com.example.medicalregistrationsystem.mapper;

import com.example.medicalregistrationsystem.pojo.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {
    //查询所有科室
    @Select("select * from department")
    public List<Department> queryAll();

    //添加科室数据
    @Insert(
            "insert into department (name,introduction)"+
                    "values (#{name},#{introduction})"
    )
    public int add(Department department);

    //删除科室
    @Delete(
            "delete from department where name=#{name}"
    )
    public int delete(Department department);

    //修改信息
    @Update("update department set introduction = #{introduction} where name = #{name}")
    public int updateInfo(Department department);
}
