package com.example.demo.service;

import com.example.demo.entity.StudentAdd;
import com.example.demo.entity.StudentsEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 接口类
 * */
public interface studentServiceI {
    //查询(所有数据)
     List<StudentsEntity> sdtsel();
     //查询没有被逻辑删除的数据
    List<StudentsEntity> adtsels();
    //按照姓名进行查询
    List<StudentsEntity> selName(String name);
    //删除(真实删除)
    void   sdtDel(Integer studentsId);
    //删除（逻辑删除）
    void   sdtDels(Integer studentsId);
    //更具姓名进行批量删除（逻辑）
    void  DelAll(String name);

    //添加
    Object sdtadd(StudentAdd studentAdd);
    //更新
    StudentsEntity sdtupd(Integer id,StudentAdd studentAdd );
    //分页查询
     Page<StudentsEntity> getPageList(Integer p, Integer s, String studentsName);


}
