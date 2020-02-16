package com.example.demo.Mapper;

import com.example.demo.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    @Select("select * from student where studentId = #{studentId}")
    Student getStudentByStudentId(@Param("studentId") String studentId);

    @Select("select * from student where name = #{name}")
    List<Student> getStudentByName(@Param("name") String name);

    @Delete("delete from student where id = #{id}")
    void deleteStudent(@Param("id") int id);

    @Insert("insert into student(studentId, name, sex, major) values (#{studentId}, #{name}, #{sex}, #{major})")
    void addStudent(@Param("studentId") String studentId, @Param("name") String name, @Param("sex") String sex, @Param("major") String major);



}
