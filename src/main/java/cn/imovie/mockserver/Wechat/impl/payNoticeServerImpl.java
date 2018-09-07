package cn.imovie.mockserver.Wechat.impl;

import cn.imovie.mockserver.Wechat.server.WechatJSServer;
import cn.imovie.mockserver.Wechat.server.payNoticeServer;
import cn.imovie.mockserver.Wechat.util.StringUtil;
import cn.imovie.mockserver.Wechat.util.XmlUtils;
import cn.imovie.mockserver.Wechat.util.httpUtil;
import cn.imovie.mockserver.sign.MD5;
import cn.imovie.mockserver.util.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class payNoticeServerImpl implements payNoticeServer {
    private static final Logger logger = LoggerFactory.getLogger(payNoticeServerImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Value("${pay.wechat.key}")
    private String Signkey;

    String   return_code="SUCCESS";
    String   result_code="SUCCESS";
    String bank_type="CMC";

    @Override
    public void  payNotify(Map<String, String> map) throws IOException, InterruptedException {
        String appid=map.get("appid");//
        String mch_id=map.get("mch_id");//
        String openid=map.get("openid");//
        String trade_type=map.get("trade_type");//
        String total_fee=map.get("total_fee");//
        String out_trade_no=map.get("out_trade_no");//
        String notifyUrl=map.get("notify_url");

        String transaction_id= StringUtil.getStringDate("yyMMddHHmmss")+StringUtil.getCode(8,0);

        Map<String, String> respMap = new HashMap<String, String>();
        respMap.put("return_code",return_code);

        if ("SUCCESS".equals(return_code)){
            respMap.put("appid",appid);
            respMap.put("mch_id",mch_id);
            respMap.put("result_code",result_code);

            if("SUCCESS".equals(result_code)){
                respMap.put("openid",openid);
                respMap.put("trade_type",trade_type);
                //
                respMap.put("nonce_str",StringUtil.getRandomString(8));
                respMap.put("total_fee",total_fee);
                respMap.put("bank_type",bank_type);
                respMap.put("cash_fee",total_fee);
                respMap.put("transaction_id",transaction_id);
                respMap.put("out_trade_no",out_trade_no);
                respMap.put("time_end",StringUtil.getStringDate("yyyyMMddHHmmss"));
            }
        }

        // 过滤map
        Map<String, String> params = SignUtils.paraFilter(respMap);

        if (params.containsKey("sign"))
            params.remove("sign");
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String signatureStr = buf.toString();
        logger.info("预签名字串：" + signatureStr );
        String sign = MD5.sign(signatureStr, "&key=" + Signkey, "utf-8").toUpperCase();
        respMap.put("sign",sign);
        logger.info("签名：" + sign );
        respMap.put("sign",sign);
        logger.info("回调通知地址：" + notifyUrl );
        String res = XmlUtils.toXml(respMap);
        logger.info("回调通知参数：" + res );

        String respString = httpUtil.httpPostRequestXML(notifyUrl, res);

        logger.debug("支付结果通知响应：" + respString);

    }
}
