package com.example.demo.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 用于postman工具jsno存放数据
 * */
@Data
public class StudentAdd {
    //学生姓名
    @NotNull(message = "学生姓名不能为空")
    private String studentsName;
    //学生性别
    @NotNull(message = "学生性别不能为空")
        private String studentsGender;
    //学生年龄
//    @NotNull(message = "年龄不能为空")
//    @Min (value = 18 ,message = "不小于18")
    @NotNull(message = "学生年龄不能为空")
    private Integer studentsAge;
    //学生手机号
    @NotNull(message = "学生手机不能为空")
    private  String studentsPhone;



}
