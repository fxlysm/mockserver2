package cn.imovie.mockserver.Wechat.impl;

import cn.imovie.mockserver.Wechat.server.WechatJSServer;
import cn.imovie.mockserver.Wechat.util.StringUtil;
import cn.imovie.mockserver.Wechat.util.XmlUtils;
import cn.imovie.mockserver.conf.SystemConf;
import cn.imovie.mockserver.sign.MD5;
import cn.imovie.mockserver.util.SignUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class WechatJsPay implements WechatJSServer {
    private static final Logger logger = LoggerFactory.getLogger(WechatJSServer.class);
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    String PFMOCK= SystemConf.WECHATMOCK_MSG;


    @Value("${pay.wechat.key}")
    private String Signkey;


    String   return_code="SUCCESS";
    String   result_code="SUCCESS";
    public void Pay(Map map, PrintWriter out, HttpServletResponse resp) throws IOException {
        String appid=map.get("appid").toString();
        String mch_id=map.get("mch_id").toString();
        String notify_url=map.get("notify_url").toString();
        String trade_type=map.get("trade_type").toString();
        String out_trade_no=map.get("out_trade_no").toString();
        String openid=map.get("openid").toString();
        String total_fee=map.get("total_fee").toString();

        Map<String, String> cacheMap = new HashMap<String, String>();
        cacheMap.put("appid",appid);
        cacheMap.put("mch_id",mch_id);
        cacheMap.put("trade_type",trade_type);
        cacheMap.put("notify_url",notify_url);
        cacheMap.put("openid",openid);
        cacheMap.put("out_trade_no",out_trade_no);
        cacheMap.put("total_fee",total_fee);
        cacheMap.put("return_code",return_code);

        // set redisCache
        if (StringUtils.isNotBlank(out_trade_no)) {
            logger.debug("set rediscache...");
            String   tmpString = JSONObject.toJSONString(cacheMap);
//            redisCache.put(out_trade_no, tmpString);
            redisTemplate.opsForHash().put(PFMOCK,out_trade_no,tmpString);

        }


        Map<String, String> respMap = new HashMap<String, String>();
        respMap.put("return_code",return_code);
        if(return_code.equals("SUCCESS")||return_code.equals("success")){
            respMap.put("appid",appid);
            respMap.put("mch_id",mch_id);
            respMap.put("nonce_str",StringUtil.getRandomString(8));
//            respMap.put("notify_url",notify_url);
            if(result_code.equals("SUCCESS")){
                respMap.put("trade_type",trade_type);
                respMap.put("prepay_id","wx201410272009395522657a690389285100");
                respMap.put("code_url","weixin：//wxpay/s/An4baqw");

            }






            Map<String, String> params = SignUtils.paraFilter(respMap);
            if (params.containsKey("sign"))
                params.remove("sign");
            StringBuilder buf = new StringBuilder((params.size() + 1) * 10);

            SignUtils.buildPayParams(buf, params, false);
            String preStr = buf.toString();
            String sign = MD5.sign(preStr, "&key=" + Signkey, "utf-8").toUpperCase();
            respMap.put("sign",sign);

        }else {
            respMap.put("return_msg","SYSTEMERROR");
        }


        resp.setHeader("Content-type", "text/xml;charset=utf-8");
        String res = XmlUtils.toXml(respMap);
        logger.debug("Pay Req" + res);
        resp.getWriter().write(res);

//        out.print(respMap);
    }
}
