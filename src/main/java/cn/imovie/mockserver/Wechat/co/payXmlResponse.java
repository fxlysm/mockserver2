package cn.imovie.mockserver.Wechat.co;

import cn.imovie.mockserver.Wechat.util.XmlUtils;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@Data
@XmlRootElement(name = "xml")
public class payXmlResponse {

    private String appid;
    private String mch_id;
    private String out_trade_no;
    private String nonce_str;
    private String total_fee;

    private String prepay_id;
    private String code_url;
    private String return_code;
    private String result_code;
    private  String trade_type;

    private String sign;

    public payXmlResponse(String appid,String mch_id,String out_trade_no,String nonce_str,String total_fee,String prepay_id,String code_url,String return_code,String result_code,String trade_type,String sign){
        super();
        this.appid=appid;
        this.mch_id=mch_id;
        this.out_trade_no=out_trade_no;
        this.nonce_str=nonce_str;
        this.total_fee=total_fee;
        this.prepay_id=prepay_id;
        this.code_url=code_url;
        this.return_code=return_code;
        this.result_code=result_code;
        this.trade_type=trade_type;
        this.sign=sign;

    }


    @XmlElement
    public String getAppid() {
        return appid;
    }
    @XmlElement
    public String getMch_id() {
        return mch_id;
    }
    @XmlElement
    public String getOut_trade_no() {
        return out_trade_no;
    }
    @XmlElement
    public String getTotal_fee() {
        return total_fee;
    }
    @XmlElement
    public String getNonce_str() {
        return nonce_str;
    }
    @XmlElement
    public String getCode_url() {
        return code_url;
    }
    @XmlElement
    public String getPrepay_id() {
        return prepay_id;
    }
    @XmlElement
    public String getResult_code() {
        return result_code;
    }
    @XmlElement
    public String getReturn_code() {
        return return_code;
    }
    @XmlElement
    public String getTrade_type() {
        return trade_type;
    }
    @XmlElement
    public String getSign() {
        return sign;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }




    @Override
    public String toString() {
//       this.appid=appid;
//       this.mch_id=mch_id;
        Map<String, String> respMap = new HashMap<String, String>();
        respMap.put("return_code",return_code);
        respMap.put("appid",appid);
        respMap.put("mch_id",mch_id);
        respMap.put("nonce_str",nonce_str);
        respMap.put("trade_type",trade_type);
        respMap.put("prepay_id",prepay_id);//wx14181649632453b2c1d4f0993887498057
        respMap.put("code_url",code_url);
        respMap.put("sign",sign);
//        resp.setHeader("Content-type", "text/xml;charset=utf-8");
//        String res = XmlUtils.toXml(respMap);
        return XmlUtils.toXml(respMap);
    }


    //    public payXmlResponse() {
//        super();
//    }

//    public payXmlResponse(String appid, String mch_id,String out_trade_no, String total_fee) {
//        this.appid = appid;
//        this.mch_id = mch_id;
//        this.out_trade_no = out_trade_no;
//        this.total_fee = total_fee;
//
//    }

}


