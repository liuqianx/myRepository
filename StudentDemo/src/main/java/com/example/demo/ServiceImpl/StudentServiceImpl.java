package com.example.demo.ServiceImpl;

import com.example.demo.Mapper.StudentMapper;
import com.example.demo.pojo.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public Student getStudentByStudentId(String studentId){
        return studentMapper.getStudentByStudentId(studentId);
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentMapper.getStudentByName(name);
    }

    @Override
    public void addStudent(String studentId, String name, String sex, String major) {
        studentMapper.addStudent(studentId, name, sex, major);
    }

    @Override
    public void deleteStudent(int id){
        studentMapper.deleteStudent(id);
    }


}
