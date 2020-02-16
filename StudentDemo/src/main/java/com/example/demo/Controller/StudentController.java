package com.example.demo.Controller;

import com.example.demo.Annotation.Log;
import com.example.demo.pojo.Student;
import com.example.demo.Service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "student Controller")
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @ApiOperation(value = "根据学生ID查询学生信息", notes = "查询")
    @Log("getStudentByStudentId")
    @RequestMapping(value = "/getStudentByStudentId", method = RequestMethod.GET)
    public Student getStudentByStudentId(@RequestParam(value = "studentId") String studentId){
        return studentService.getStudentByStudentId(studentId);
    }

    @ApiOperation(value = "根据学生名字查询学生信息", notes = "查询")
    @Log("getStudentByName")
    @RequestMapping(value = "/getStudentByName", method = RequestMethod.GET)
    public List<Student> getStudentByName(@RequestParam(value = "name") String name){
        return studentService.getStudentByName(name);
    }

    @ApiOperation(value = "添加一个学生", notes = "添加")
    @Log("addStudent")
    @RequestMapping(value = "/addStudent", method = {RequestMethod.POST})
    public void addStudent(@RequestParam(value = "studentId") String studentId, @RequestParam(value = "name") String name, @RequestParam(value = "sex")String sex, @RequestParam(value = "major") String major){
        studentService.addStudent(studentId, name, sex, major);
    }

    @ApiOperation(value = "删除一个学生", notes = "删除")
    @Log("deleteStudent")
    @RequestMapping(value = "/deleteStudent", method = RequestMethod.DELETE)
    public void deleteStudent(@RequestParam(value = "id") int id){
        studentService.deleteStudent(id);
    }


}
