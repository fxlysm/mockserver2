package cn.imovie.mockserver.Wechat.co;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.json.JSONObject;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data //lombok注解，无需手动添加getter/setter
@XmlRootElement(name = "xml")
@ToString //lombok注解，无需手动添加toString方法
public class XmlRequest implements Serializable {
    @NotEmpty(message = "appid 不能为空")
    private  String appid;
    private String mch_id;
    private String out_trade_no;
    private String total_fee;
    private String notify_url;
    private String trade_type;
    private String spbill_create_ip;
    private String body;
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

        return jsonObjArr.toString();
    }
}
