package com.example.demo.repositery;

import com.example.demo.entity.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Jpa类
 * */
@Repository         //
public interface studentsRepositery extends JpaRepository<StudentsEntity,Integer>, JpaSpecificationExecutor<StudentsEntity> {
      public List<StudentsEntity>findBystudentsDel(Integer studentsDel);
     //用姓名来进行查询
      public List<StudentsEntity> findByStudentsNameEquals(String studentsName);

}
