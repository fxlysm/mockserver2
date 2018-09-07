package cn.imovie.mockserver.Wechat.impl;


import cn.imovie.mockserver.Wechat.server.orderqueryServer;
import cn.imovie.mockserver.Wechat.util.StringUtil;
import cn.imovie.mockserver.Wechat.util.XmlUtils;
import cn.imovie.mockserver.sign.MD5;
import cn.imovie.mockserver.util.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class orderqueryImpl implements orderqueryServer{
    private static final Logger logger = LoggerFactory.getLogger(orderqueryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${pay.wechat.key}")
    private String Signkey;

    @Override
    public void  Transactionstatusquery(Map<String, String> map,HttpServletResponse resp)  throws IOException {

       String return_code="SUCCESS";
        String result_code="SUCCESS";
        String trade_state="SUCCESS";
        String transaction_id="",out_trade_no="";
        Map<String,Object> translogs=null;
        if(map.containsKey("transaction_id")){
             transaction_id=map.get("transaction_id");

            String command="SELECT * FROM wechat_translog WHERE transaction_id='"+transaction_id+"'";
            logger.info("command:"+command);
            translogs=  jdbcTemplate.queryForMap(command);

        }else {
            if(map.containsKey("out_trade_no")){
                 out_trade_no=map.get("out_trade_no");

                String command="SELECT * FROM wechat_translog WHERE out_trade_no='"+out_trade_no+"'";
                logger.info("command:"+command);
               translogs=  jdbcTemplate.queryForMap(command);

            }else {

            }


        }

        Map<String, String> respMap = new HashMap<String, String>();
        respMap.put("return_code",return_code);
        if(return_code.equals("SUCCESS")){

            respMap.put("return_code",translogs.get("appid").toString());
            respMap.put("mch_id",translogs.get("mch_id").toString());
            respMap.put("nonce_str",StringUtil.getRandomString(8));

            if(result_code.equals("SUCCESS")){
                respMap.put("openid",translogs.get("openid").toString());
                respMap.put("trade_type",translogs.get("trade_type").toString());
                respMap.put("trade_state",trade_state);
                respMap.put("bank_type","CMC");
                respMap.put("total_fee",translogs.get("total_fee").toString());
                respMap.put("cash_fee",translogs.get("cash_fee").toString());
                respMap.put("transaction_id",translogs.get("transaction_id").toString());
                respMap.put("time_end",translogs.get("pay_time").toString());
                respMap.put("trade_state_desc","SUCCESS");
            }

        }else {
            respMap.put("return_msg",translogs.get("sign error").toString());
        }

        logger.info(respMap.toString());

        Map<String, String> params = SignUtils.paraFilter(respMap);
        if (params.containsKey("sign"))
            params.remove("sign");
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);

        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        logger.info("preStr:" + preStr);
        String sign = MD5.sign(preStr, "&key=" + Signkey, "utf-8").toUpperCase();
        respMap.put("sign",sign);

        resp.setHeader("Content-type", "text/xml;charset=utf-8");
        String res = XmlUtils.toXml(respMap);
        logger.info("Pay Req" + res);
        resp.getWriter().write(res);

    }
}
