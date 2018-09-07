package cn.imovie.mockserver.Alipay.server.Impl;

import cn.imovie.mockserver.Alipay.controller.AlipayPayServer;
import cn.imovie.mockserver.Alipay.server.tradepayServer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Service
public class Alipaytradepay  implements tradepayServer {
    private static final Logger logger = LoggerFactory.getLogger(Alipaytradepay.class);


    public void Pay(Map map,HttpServletResponse resp) throws Exception{
        String biz_content= map.get("biz_content").toString();
        String method=map.get("method").toString();
        String app_id=map.get("app_id").toString();
        String sign_type=map.get("sign_type").toString();
        String charset=map.get("charset").toString();
        String version=map.get("version").toString();


        JSONObject bizcontent = new JSONObject(biz_content); //解析JSON字符串

        JSONArray goods_detail =bizcontent.getJSONArray("goods_detail");//接收JSON对象里的数组//用来接收JSON对象里的数组
        int lonth=goods_detail.length();

        JSONObject jsonObject = new JSONObject(biz_content);
        System.out.println("  " + jsonObject.getString("out_trade_no"));
        logger.info("out_trade_no;");
        logger.info(jsonObject.getString("out_trade_no"));


    }



    public void PrecreatePay(Map map,HttpServletResponse resp) throws Exception{

    }

    public void CreatePay(Map map,HttpServletResponse resp) throws Exception{

    }


    public void Query(Map map,HttpServletResponse resp) throws Exception{

    }
}
