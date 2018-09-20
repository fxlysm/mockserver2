package cn.imovie.mockserver.Wechat.co;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.json.JSONObject;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data //lombok注解，无需手动添加getter/setter
@XmlRootElement //(name = "xml")
public class PayRequest extends XmlRequest implements Serializable {


//    @NotEmpty(message = "appid cant null")
//    @ApiModelProperty(value = "appid", required=true,notes="APPID")
//    public    String appid;
//
//    @ApiModelProperty(notes = "商户ID", value = "商户ID", required = true)
//    @NotEmpty(message = "mch_id cant null")
//    public  String mch_id;
//
//
//
//    @NotEmpty(message = "nonce_str cant null")
//    @ApiModelProperty(value = "随机字符串", required=true,notes="随机字符串")
//    public  String nonce_str;
//
//    @ApiModelProperty(notes = "签名", value = "签名", required = true)
//    @NotEmpty(message = "sign cant null")
//    public  String sign;



    @ApiModelProperty(notes = "商户交易流水号", value = "商户交易流水号", required = true)
    @NotEmpty(message = "out_trade_no cant null")
    private String out_trade_no;

    @ApiModelProperty(value = "交易额", required = true)
    @NotEmpty(message = "total_fee cant null")
    @DecimalMin(value = "1",message = "total_fee >=1 fen")
    @DecimalMax(value = "1000000",message = "total_fee < 10000 yuan")
    private String total_fee;

    @ApiModelProperty(value = "支付通知", required = true)
    @NotEmpty(message = "notify_url cant null")
    private String notify_url;

    @ApiModelProperty(value = "业务类型", required = true)
    @NotEmpty(message = "trade_type cant null")
    private String trade_type;

    @ApiModelProperty(value = "终端IP", required = true)
    @NotEmpty(message = "spbill_create_ip cant null")
    private String spbill_create_ip;

    @NotEmpty(message = "body cant null")
    @ApiModelProperty(value = "商品描述", required = true)
    private String body;

    @ApiModelProperty(notes = "用户标识openID", value = "用户标识openid", required = false)
    private String openid;


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
//        jsonObjArr.put("transaction_id",transaction_id);


        return jsonObjArr.toString();
    }
}
