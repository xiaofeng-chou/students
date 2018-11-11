package com.example.demo.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用于postman工具jsno存放数据
 * */
@Data
public class StudentAdd {
    private String      studentsName;
    //学生性别
    private String   studentsGender;
    //学生年龄
    @NotNull(message = "年龄不能为空")
    private Integer     studentsAge;
    //学生手机号
    private  String    studentsPhone;


}
