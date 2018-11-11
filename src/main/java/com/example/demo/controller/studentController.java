package com.example.demo.controller;
import com.example.demo.entity.StudentAdd;
import com.example.demo.entity.StudentsEntity;
import com.example.demo.service.studentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * URL类
 * */

@Slf4j              //日志注解
@RestController             //映射资源
public class studentController {
    //查询所有的学生类(表中所有数据)
    @GetMapping(value = "/selectss")
    public List<StudentsEntity> select(){
        return  studentService.sdtsel ();
    }

    //查询没有被逻辑删除的数据
    @GetMapping(value = "/se")
    public List<StudentsEntity> selects(){
        return  studentService.adtsels ();
    }
    //添加学生信息
    @PutMapping(value = "/add")
    public Object add(@RequestBody @Valid StudentAdd StudentAdd,BindingResult bindingResult){                  // @Valid 表达验证，
        if (bindingResult.hasErrors ()){                //是否报错信息
            System.out.println (bindingResult.getFieldError ().getDefaultMessage ());           //输出报错信息
            return "年龄不能为空！！";
        }
        return studentService.sdtadd (StudentAdd);
    }
    //修改学生信息
    @PutMapping(value = "/upd/{id}")
    public StudentsEntity upd(@PathVariable(value = "id") Integer id,@RequestBody StudentAdd studentAdd){return studentService.sdtupd (id,studentAdd);}
    //删除学生信息（逻辑删除）
    @DeleteMapping(value = "/dels/{id}")
    public String dels(@PathVariable(value = "id") Integer id){
        studentService.sdtDels (id);
        return "删除成功！";

    }
    //删除学生信息（真实删除）
    @DeleteMapping(value = "/del/{id}")
    public String del(@PathVariable(value = "id") Integer id){
        studentService.sdtDel (id);
        return "删除成功！";
    }
    @Autowired
    private studentService studentService;
}
