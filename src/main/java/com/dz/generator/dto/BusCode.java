package com.dz.generator.dto;

public enum BusCode {

    SUCCESS(1,"成功"),
    FAIL(0,"失败");

    private int code;
    private String msg;

    BusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
