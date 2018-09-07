package cn.imovie.mockserver.taopiaopiao.dao;


import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


//@Data
public class TppDao implements Serializable {//



    @NotEmpty(message = "method cannot to null")
   private String  method;


    @NotEmpty(message = "app_key cannot to null")
    private String  app_key;


    @NotEmpty(message = "sign_method cannot to null")
    private String  sign_method;


    @NotEmpty(message = "sign cannot to null")
    private  String sign;


    @NotEmpty(message = "timestamp cannot to null")
    private String timestamp;

//    @FlagValidator(values = "2.0,3.0,1.0",message = "v only set 2.0 ")

    @NotEmpty(message = "version cannot to null")
    private String v;





    public void setSign(String sign) {
        this.sign = sign;
    }
    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public void setSign_method(String sign_method) {
        this.sign_method = sign_method;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public void setV(String v) {
        this.v = v;
    }

    public String getSign() {
        return sign;
    }
    public String getApp_key() {
        return app_key;
    }
    public String getMethod() {
        return method;
    }
    public String getSign_method() {
        return sign_method;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public String getV() {
        return v;
    }


//    @Override
//    public String toString() {
//        this.app_key=app_key;
//        this.method=method;
//        this.sign=sign;
//        this
//        return super.toString();
//    }


//    @Override
//    public String toString() {
//        return super.toString();
//    }
@Override
    public String toString(){
        return "{" +
                "\"method\":\"" + getMethod() +"\""+
                "\"app_key\":\"" + getApp_key()+ "\""+
                "\"sign_method\":\"" + getSign_method() + "\"" +
                "\"sign\":\"" + getSign() + "\"" +
                "\"timestamp\":\"" + getTimestamp() + "\"" +
                "\"v\":\"" + v + "\"" +
                '}';

    }
}
