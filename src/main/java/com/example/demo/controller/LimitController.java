package com.example.demo.controller;

import com.example.demo.entity.StudentsEntity;
import com.example.demo.service.studentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 分页查询---失败
 * */
@Slf4j
@RestController
public class LimitController {

    @GetMapping(value = "/limit")
    public List<StudentsEntity > limit(){
       List<StudentsEntity> ls = studentService.sdtsel ();
       int it =  ls.size ();
       for (int i=1;ls.size ()>20;){
           ls.size ();
        return ls;
       }
        return null;
    }

    @Autowired
    private studentService studentService;
}
