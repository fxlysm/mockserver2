package cn.imovie.mockserver.Wechat.co;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "xml")
public class payXmlResponse {

    private String appid;
    private String mch_id;
    private String out_trade_no;
    private String total_fee;

    public payXmlResponse() {
    }

    public payXmlResponse(String appid, String mch_id,String out_trade_no, String total_fee) {
        this.appid = appid;
        this.mch_id = mch_id;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;

    }

}


