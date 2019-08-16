package com.dz.generator.dto;

import java.util.Date;

public class BusResult {

    private int code;
    private String message;
    private Object data;

    public BusResult() {
    }


    public static BusResult build(BusCode busCode){
        return BusResult.build(busCode,null);
    }

    public static BusResult build(BusCode busCode,Object data){
        BusResult result = new BusResult();
        result.init(busCode.getCode(),busCode.getMsg(),data);
        return result;
    }

    private void init(int code,String msg,Object data){
        this.setCode(code);
        this.setMessage(msg);
        this.setData(data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
