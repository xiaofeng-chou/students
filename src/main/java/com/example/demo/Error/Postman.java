package com.example.demo.Error;

/*
* postman 工具返回格式
* */
public class Postman {
    public static Resut successful( Object oj){
        Resut rt =new Resut ();
        rt.setCode (0);
        rt.setMsg ("成功");
        rt.setData (oj);
        return rt;
    }
    public static  Resut succes(){
        return successful (null);
    }
    public static Resut error(Integer code,String msg){
        Resut rt =new Resut ();
        rt.setCode (code);
        rt.setMsg (msg);
        return rt;

    }
}
