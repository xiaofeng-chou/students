package com.example.demo.Error;


public class Resut<T> {
    //错误码
    private  Integer Code;
    //返回结果
    private  String  Msg;
    //数据
    private  T  Data;

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
