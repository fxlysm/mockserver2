package cn.imovie.mockserver.Alipay.co;



import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AlipayPay implements Serializable {
    @ApiModelProperty(value = "APP_ID", required = true)
    @NotEmpty(message = "app_id cannot to null\n")
    private  String app_id;

    @ApiModelProperty(value = "请求接口名", required = true)
    @NotEmpty(message = "method cannot to null\n")
    private  String method;

    @ApiModelProperty(value = "字符类型", required = true)
    @FlagValidator(values = "utf-8,gbk,gb231",message = "charset only set utf-8,gbk,gb231 ")
    private  String charset;

    @ApiModelProperty(value = "签名类型", required = true)
    @NotEmpty(message = "sign_type cannot to null\n")
    @FlagValidator(values = "RSA2",message = "sign_type only set RSA2")
    private  String sign_type;

    @ApiModelProperty(value = "签名字符串", required = true)
    @NotEmpty(message = "sign cannot to null\n")
    private  String sign;

    @ApiModelProperty(value = "时间戳", required = true)
    private  String timestamp;

    @ApiModelProperty(value = "版本", required = true)
    private  String version;

    @ApiModelProperty(value = "业务内容", required = true)
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
