package com.example.demo;

import com.example.demo.Service.StudentService;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.SysLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
//@Transactional
class DemoApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;


    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test1() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/student/getStudentByStudentId").param("studentId","N1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.studentId").value("N1"))
        .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void test2() throws Exception{
        Student student1 = this.studentService.getStudentByStudentId("N1");
        System.out.println(student1.toString());
        Assert.assertEquals("ql", student1.getName());

        Student student2 = this.studentService.getStudentByStudentId("N2");
        System.out.println(student2.toString());
        Assert.assertEquals("qwe", student2.getName());

        Student student3 = (this.studentService.getStudentByName("qqq")).get(0);
        System.out.println(student3.toString());
        Assert.assertEquals("N4", student3.getStudentId());

    }

}
