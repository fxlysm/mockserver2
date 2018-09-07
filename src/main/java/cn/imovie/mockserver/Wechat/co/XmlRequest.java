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
    private  String appid;

    @ApiModelProperty(notes = "商户ID", value = "mch_id", required = true)
    @NotEmpty(message = "mch_id cant null")
    private String mch_id;
    @NotEmpty(message = "out_trade_no cant null")
    private String out_trade_no;
    @NotEmpty(message = "total_fee cant null")
    @DecimalMin(value = "1",message = "total_fee >=1 fen")
    @DecimalMax(value = "1000000",message = "total_fee < 10000 yuan")
    private String total_fee;
    @NotEmpty(message = "notify_url cant null")
    private String notify_url;
    @NotEmpty(message = "trade_type cant null")
    private String trade_type;
    private String spbill_create_ip;
    private String body;
    @ApiModelProperty(notes = "openID", value = "openid", required = false)
    private String openid;

    @ApiParam(name = "transaction_id", value = "transaction_id", required = false)
    private String transaction_id;


    @Override
    public String toString() {
        JSONObject jsonObjArr = new JSONObject();
        jsonObjArr.put("appid",appid);
        jsonObjArr.put("mch_id",mch_id);
        jsonObjArr.put("out_trade_no",out_trade_no);
        jsonObjArr.put("total_fee",total_fee);
        jsonObjArr.put("notify_url",notify_url);
        jsonObjArr.put("trade_type",trade_type);
        jsonObjArr.put("spbill_create_ip",spbill_create_ip);
        jsonObjArr.put("body",body);
        jsonObjArr.put("openid",openid);
        jsonObjArr.put("transaction_id",transaction_id);


        return jsonObjArr.toString();
    }
}
