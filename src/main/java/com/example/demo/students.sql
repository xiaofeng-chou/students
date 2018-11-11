DROP  DATABASE IF EXISTS `students_db`

CREATE DATABASE `students_db`
  DEFAULT CHARACTER SET `UTF-8 `
  COLLATE `UTF8_ general_ci`;

USE `students_db`;

CREATE TABLE students(
  `students_id` BIGINT UNSIGNED AUTO INCREMENT PRIMARY KEY, --学生id
  `students_name` VARCHAR (10),  --学生姓名
  `studentsGender` VARCHAR (2),  --学生性别
  `studentsAge` INTEGER , --学生年龄
  `studentsPhone` VARCHAR (20),--学生手机号
  `studentsDel` TINYINT UNSIGNED --是否删除
)