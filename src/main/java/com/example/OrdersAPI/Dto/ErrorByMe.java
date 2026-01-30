package com.example.OrdersAPI.Dto;

public class ErrorByMe {
    private String msg;
    public ErrorByMe(){

    }
    public ErrorByMe(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
