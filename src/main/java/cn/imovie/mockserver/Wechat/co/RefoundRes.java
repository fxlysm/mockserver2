package cn.imovie.mockserver.Wechat.co;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.json.JSONObject;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data //lombok注解，无需手动添加getter/setter
@XmlRootElement //(name = "xml")
public class RefoundRes extends XmlRequest{



@ApiModelProperty(value = "商户订单号", required = true)
@NotEmpty(message = "out_trade_no cant null")
private String out_trade_no;

   @ApiModelProperty(value = "微信订单号", required = true)
    @NotEmpty(message = "transaction_id cant null")
    private String transaction_id;

    @ApiModelProperty(value = "订单金额", required = true)
    @NotEmpty(message = "total_fee cant null")
    @DecimalMin(value = "1",message = "total_fee >=1 fen")
    @DecimalMax(value = "1000000",message = "total_fee < 10000 yuan")
    private String total_fee;

    @ApiModelProperty(value = "退款金额", required = true)
    @NotEmpty(message = "refund_fee cant null")
    @DecimalMin(value = "1",message = "refund_fee >=1 fen")
    @DecimalMax(value = "1000000",message = "refund_fee < 10000 yuan")
    private String refund_fee;

    @ApiModelProperty(value = "签名类型", required = false)
    private String sign_type;



    @ApiModelProperty(value = "退款货币种类", required = false)
    private String refund_fee_type;


    @ApiModelProperty(value = "退款原因", required = false)
    private String refund_desc;

    @ApiModelProperty(value = "退款资金来源", required = false)
    private String refund_account;


    @ApiModelProperty(value = "退款结果通知url", required = false)
    private String notify_url;
//
//    @Override
//    public String toString() {
//        JSONObject jsonObjArr = new JSONObject();
//        jsonObjArr.put("appid",appid);
//        jsonObjArr.put("mch_id",mch_id);
//        jsonObjArr.put("out_trade_no",out_trade_no);
//        jsonObjArr.put("total_fee",total_fee);
//        jsonObjArr.put("notify_url",refund_fee);
//        jsonObjArr.put("transaction_id",transaction_id);
//
//
//        return jsonObjArr.toString();
//    }

 @Override
 public String toString() {
  JSONObject jsonObjArr = new JSONObject();
  jsonObjArr.put("appid",appid);
  jsonObjArr.put("mch_id",mch_id);
  jsonObjArr.put("out_trade_no",out_trade_no);
  jsonObjArr.put("transaction_id",transaction_id);
  jsonObjArr.put("out_trade_no",total_fee);
  jsonObjArr.put("transaction_id",refund_fee);
  jsonObjArr.put("sign_type",sign_type);
  return jsonObjArr.toString();
 }
}
