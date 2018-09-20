package cn.imovie.mockserver.Wechat.co;

import io.swagger.annotations.*;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data //lombok注解，无需手动添加getter/setter
@XmlRootElement //(name = "xml")
//@ToString //lombok注解，无需手动添加toString方法
public class XmlRequest implements Serializable {

    @NotEmpty(message = "appid cant null")
    @ApiModelProperty(value = "appid", required=true,notes="APPID")
    public    String appid;

    @ApiModelProperty(notes = "商户ID", value = "商户ID", required = true)
    @NotEmpty(message = "mch_id cant null")
    public  String mch_id;



    @NotEmpty(message = "nonce_str cant null")
    @ApiModelProperty(value = "随机字符串", required=true,notes="随机字符串")
    public  String nonce_str;

    @ApiModelProperty(notes = "签名", value = "签名", required = true)
    @NotEmpty(message = "sign cant null")
    public  String sign;



//    @ApiParam(name = "transaction_id", value = "transaction_id", required = false)
//    private String transaction_id;



}
