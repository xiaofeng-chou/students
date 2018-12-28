package com.example.demo.service;
import com.example.demo.entity.StudentAdd;
import com.example.demo.entity.StudentsEntity;
import com.example.demo.repositery.studentsRepositery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 接口实现类
 * */
@Slf4j      //错误日志注解
@Service        //用于url接收入口
public class studentService implements studentServiceI {                    //实现接口
    //查询(表中所有数据)
    @Override
    public List<StudentsEntity> sdtsel() {
        return studentsRepositery.findAll(); }
    //查询没有被逻辑删除的数据
    @Override
    public List<StudentsEntity> adtsels() {
        return studentsRepositery.findBystudentsDel(1);
    }
    //按照姓名进行查询
    @Override
    public List<StudentsEntity> selName(String name) {
        return studentsRepositery.findByStudentsNameEquals (name);
    }

    //删除(真实删除)
    @Override
    public void sdtDel(Integer studentsId) {
         studentsRepositery.deleteById (studentsId);
    }
    //逻辑删除
    @Override
    public void sdtDels(Integer studentsId) {
        StudentsEntity se =new StudentsEntity ();
        if (se.getStudentsId () == studentsId ){
            se.setStudentsId (0);
        }else {
            System.out.println ("数据库没有查到这个id");
        }
    }
    //更具姓名进行批量删除（逻辑）
    @Override
    public void DelAll(String name) {
        //方法一：
        OnedelAll(name);
        //方法二：
//        TowdelAll (name);
    }

    //添加
    @Override
    public Object sdtadd(StudentAdd studentAdd) {
       String sg ="[0-9]{11}";
       String obj = studentAdd.getStudentsPhone ();
        if(Pattern.matches (sg,obj)){
            if(obj.length () == 11 & studentAdd.getStudentsGender().equals ("男") || studentAdd.getStudentsGender().equals ("女") )
            {
                StudentsEntity se  =new StudentsEntity ();
                se.setStudentsId (null);
                se.setStudentsAge (studentAdd.getStudentsAge ());
                se.setStudentsName (studentAdd.getStudentsName ());
                se.setStudentsGender (studentAdd.getStudentsGender ());
                se.setStudentsPhone (studentAdd.getStudentsPhone ());
                se.setStudentsDel (1);
                return studentsRepositery.save (se);
            }
        }
         return "StudentsAge(性别)：只能填写 男/女 , \nStudentsPhone(手机号):不正确 ' "  ;
    }
    //更新学生信息
    @Override
    public StudentsEntity sdtupd( Integer id,StudentAdd studentAdd) {
        StudentsEntity se =new StudentsEntity ();
        se.setStudentsId (id);
        se.setStudentsAge (studentAdd.getStudentsAge ());
        se.setStudentsName (studentAdd.getStudentsName ());
        se.setStudentsGender (studentAdd.getStudentsGender ());
        se.setStudentsPhone (studentAdd.getStudentsPhone ());
        return studentsRepositery.save (se);
    }
    //分页查询
    @Override
    public Page<StudentsEntity> getPageList(Integer p, Integer s, String studentsName) {
        Page<StudentsEntity> page = studentsRepositery.findAll ((root, query, cb) ->
        {
            List<Predicate> predicate = new ArrayList<>();
            if(studentsName != null && studentsName.trim().length() != 0)
                predicate.add(cb.like(root.get("students_name").as(String.class), studentsName + "%"));  // 用户标识
            // 加入过滤条件
            query.where(predicate.toArray(new Predicate[predicate.size()]));
            return null;
        }, PageRequest.of(p - 1, s));
        return page;
    }


    /**
     * 更具姓名进行批量删除（逻辑删除）
     **/
    //方法一：
    public void OnedelAll(String name){
      List<StudentsEntity> studentsEntityList = studentsRepositery.findAll ();
      List<StudentsEntity> requ=new ArrayList<> ();
      studentsEntityList
              .parallelStream ()
              .filter (studentsEntity -> studentsEntity.getStudentsName ().equals (name))
              .forEach (studentsEntity -> {
                  studentsEntity.setStudentsDel (1);
                  requ.add (studentsEntity);
              });
            studentsRepositery.saveAll (requ);

    }
    //方法二：
    public void TowdelAll(String name){
        List<StudentsEntity> studenList = studentsRepositery.findAll ();
        List<StudentsEntity> ret2=new ArrayList<> ();
        studenList.forEach (studentsEntity -> {
            if (studentsEntity.getStudentsName ().equals (name)){
                studentsEntity.setStudentsDel (1);
                ret2.add (studentsEntity);
            }});
        studentsRepositery.saveAll (ret2);
    }

    @Autowired              //
    private studentsRepositery studentsRepositery;
}
