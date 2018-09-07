package cn.imovie.mockserver.Wechat.co;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Map;

@XmlRootElement
public class reqbody implements Serializable {
    private String appid;
    private String mch_id;
    private String out_trade_no;
    private String total_fee;
    private String notify_url;
    private String trade_type;
    private String spbill_create_ip;
    private String body;
    public reqbody() {
        super();
        // TODO Auto-generated constructor stub
    }
    public reqbody(String appid, String mch_id,String out_trade_no, String total_fee) {
        this.appid = appid;
        this.mch_id = mch_id;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;

    }
    public String getappid() {
        return appid;
    }
    // 该属性作为xml的element
    @XmlElement
    public void setappid(String appid) {
        this.appid = appid;
    }

    public String getmch_id() {
        return mch_id;
    }
    @XmlElement
    public void setmch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getout_trade_no() {
        return out_trade_no;
    }
    @XmlElement
    public void setout_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }


    public String gettotal_fee() {
        return total_fee;
    }
    @XmlElement
    public void settotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    @Override
    public String toString() {
        Map<String, String> tomap = null;
        tomap.put("appid", appid);
        tomap.put("mch_id", mch_id);
        tomap.put("out_trade_no", out_trade_no);

        return tomap.toString();
//        return "Book [appid=" + appid + ", out_trade_no=" + out_trade_no + ", total_fee=" + total_fee + "]";
    }
}
