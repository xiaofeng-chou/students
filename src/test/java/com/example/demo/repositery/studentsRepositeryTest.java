package com.example.demo.repositery;

import com.example.demo.entity.StudentsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith (SpringRunner.class)
@SpringBootTest
public class studentsRepositeryTest {
    @Autowired
    private  studentsRepositery studentsRepositery;
    @Test
   public  void  findOnTest(){
    }
}