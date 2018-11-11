package com.example.demo.service;
import com.example.demo.entity.StudentAdd;
import com.example.demo.entity.StudentsEntity;
import com.example.demo.repositery.studentsRepositery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    //删除(真实删除)
    @Override
    public void sdtDel(Integer studentsId) {
         studentsRepositery.deleteById (studentsId);
    }
    //逻辑删除
    @Override
    public void sdtDels(Integer studentsId) {
        StudentsEntity se =new StudentsEntity ();
        if (se.getStudentsId () ==studentsId ){
            se.setStudentsId (0);
        }else {
            System.out.println ("数据库没有查到这个id");
        }
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


    @Autowired              //
    private studentsRepositery studentsRepositery;
}
