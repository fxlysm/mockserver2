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
public class RefoundRes implements Serializable {


    @NotEmpty(message = "appid cant null")
    @ApiModelProperty(value = "appid", required=true,notes="APPID")
    private  String appid;

    @ApiModelProperty(notes = "商户ID", value = "mch_id", required = true)
    @NotEmpty(message = "mch_id cant null")
    private String mch_id;

    @NotEmpty(message = "out_trade_no cant null")
    private String out_trade_no;

    @NotEmpty(message = "transaction_id cant null")
    private String transaction_id;

    @NotEmpty(message = "total_fee cant null")
    @DecimalMin(value = "1",message = "total_fee >=1 fen")
    @DecimalMax(value = "1000000",message = "total_fee < 10000 yuan")
    private String total_fee;

    @NotEmpty(message = "refund_fee cant null")
    @DecimalMin(value = "1",message = "refund_fee >=1 fen")
    @DecimalMax(value = "1000000",message = "refund_fee < 10000 yuan")
    private String refund_fee;




    @Override
    public String toString() {
        JSONObject jsonObjArr = new JSONObject();
        jsonObjArr.put("appid",appid);
        jsonObjArr.put("mch_id",mch_id);
        jsonObjArr.put("out_trade_no",out_trade_no);
        jsonObjArr.put("total_fee",total_fee);
        jsonObjArr.put("notify_url",refund_fee);
        jsonObjArr.put("transaction_id",transaction_id);


        return jsonObjArr.toString();
    }
}
