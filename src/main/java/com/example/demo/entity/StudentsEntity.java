package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 实体类
 * */
@Data               //自动生成get和set
@Entity             //实体类注解
@Table(name = "students")       //对应数据库表名
public class StudentsEntity implements Serializable {

    //学生ID
    @Id
    @GeneratedValue
    private Integer studentsId;
    //学生姓名
    private String studentsName;
    //学生性别
    private String studentsGender;
    //学生年龄
    private Integer studentsAge;
    //学生手机号
    private  String studentsPhone;
    //是否删除
    private  Integer studentsDel;


}
