package cn.imovie.mockserver.Wechat.co;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.json.JSONObject;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;

@Data //lombok注解，无需手动添加getter/setter
@XmlRootElement //(name = "xml")
public class Orderquery extends XmlRequest{

    @ApiModelProperty(value = "商户订单号", required = true)
    @NotEmpty(message = "out_trade_no cant null")
    private String out_trade_no;

    @ApiModelProperty(value = "微信订单号", required = true)
    @NotEmpty(message = "transaction_id cant null")
    private String transaction_id;


    @ApiModelProperty(value = "签名类型", required = false)
    private String sign_type;


    @Override
    public String toString() {
        JSONObject jsonObjArr = new JSONObject();
        jsonObjArr.put("appid",appid);
        jsonObjArr.put("mch_id",mch_id);
        jsonObjArr.put("out_trade_no",out_trade_no);
        jsonObjArr.put("transaction_id",transaction_id);
        jsonObjArr.put("sign_type",sign_type);
        return jsonObjArr.toString();
    }
}
