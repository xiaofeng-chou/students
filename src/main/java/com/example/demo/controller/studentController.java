package com.example.demo.controller;
import com.example.demo.Eorr.Resut;
import com.example.demo.entity.StudentAdd;
import com.example.demo.entity.StudentsEntity;
import com.example.demo.repositery.studentsRepositery;
import com.example.demo.service.studentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.HashMap;
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
    public Object add(@RequestBody @Valid StudentAdd studentAdd,BindingResult bindingResult){
        if (bindingResult.hasErrors ()){
            //是否报错信息
            System.out.println (bindingResult.getFieldError ().getDefaultMessage ());           //输出报错信息
            return "学生年龄必须大于18";
        }
        return studentService.sdtsel ();

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
    //分页查询
    @RequestMapping(value = "/selpage")
    public Object page(@RequestParam("p") Integer p, @RequestParam("s") Integer s, @RequestParam(value = "name", required = false) String studentsName)
    {
        Page<StudentsEntity> page = studentService.getPageList(p, s, studentsName);
        return new HashMap<String, Object> ()
        {{
            put("data", page.getContent());     //返回数据
            put("totalElements", page.getTotalElements());  //数据库中数据条数
            put("totalPages", page.getTotalPages());    //返回页数（）
            put("p", p);
            put("s", s);
        }};
    }


    @Autowired
    private studentService studentService;
}
