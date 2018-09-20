package cn.imovie.mockserver.Wechat.co;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.json.JSONObject;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;

@Data //lombok注解，无需手动添加getter/setter
@XmlRootElement //(name = "xml")
public class BillDownload  extends XmlRequest{

    @ApiModelProperty(value = "对账单日期", required = true)
    @NotEmpty(message = "bill_date cant null")
    private String bill_date;

    @ApiModelProperty(value = "账单类型(ALL，返回当日所有订单信息，默认值\n" +
            "\n" +
            "SUCCESS，返回当日成功支付的订单\n" +
            "\n" +
            "REFUND，返回当日退款订单\n" +
            "\n" +
            "RECHARGE_REFUND，返回当日充值退款订单)", required = true)
    @NotEmpty(message = "bill_date cant null")
    private String bill_type;

    @ApiModelProperty(value = "签名类型", required = false)
    private String sign_type;

    @ApiModelProperty(value = "压缩账单(GZIP)", required = false)
    private String tar_type;


    @Override
    public String toString() {
        JSONObject jsonObjArr = new JSONObject();
        jsonObjArr.put("appid",appid);
        jsonObjArr.put("mch_id",mch_id);
        jsonObjArr.put("sign",sign);
        jsonObjArr.put("nonce_str",nonce_str);
        jsonObjArr.put("bill_date",bill_date);
        jsonObjArr.put("bill_type",bill_type);

        return jsonObjArr.toString();
    }

}
