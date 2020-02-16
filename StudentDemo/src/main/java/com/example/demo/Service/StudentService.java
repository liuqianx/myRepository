package com.example.demo.Service;

import com.example.demo.pojo.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "student")
public interface StudentService {

    @Cacheable(key = "#p0")
    Student getStudentByStudentId(String studentId);

    @Cacheable(key = "#p0")
    List<Student> getStudentByName(String name);

//    @Cacheable(key = "#p0.id")
    void addStudent(String studentId, String name, String sex, String major);

//    @CacheEvict(key = "#p0")
    void deleteStudent(int id);

}
