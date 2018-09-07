package cn.imovie.mockserver.Alipay.co;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AlipayPay implements Serializable {
    @NotEmpty(message = "app_id cannot to null\n")
    private  String app_id;
    @NotEmpty(message = "method cannot to null\n")
    private  String method;
    @FlagValidator(values = "utf-8,gbk,gb231",message = "charset only set utf-8,gbk,gb231 ")
    private  String charset;
    @NotEmpty(message = "sign_type cannot to null\n")
    @FlagValidator(values = "RSA2",message = "sign_type only set RSA2")
    private  String sign_type;
    @NotEmpty(message = "sign cannot to null\n")
    private  String sign;
    private  String timestamp;
    private  String version;

    private String biz_content;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }
    public String getMethod() {
        return method;
    }
    public String getSign() {
        return sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getVersion() {
        return version;
    }

    public String getSign_type() {
        return sign_type;
    }

    public String getCharset() {
        return charset;
    }

    public String getBiz_content() {
        return biz_content;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setBiz_content(String biz_content) {
        this.biz_content = biz_content;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
