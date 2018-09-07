package cn.imovie.mockserver.Wechat.controller;

import cn.imovie.mockserver.Wechat.server.WechatJSServer;
import cn.imovie.mockserver.Wechat.co.XmlRequest;
import cn.imovie.mockserver.util.MapJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="pay")
public class WechatPayServer {

    @Autowired
    private WechatJSServer wechatJSServer;

    private static final Logger logger = LoggerFactory.getLogger(WechatPayServer.class);
//    统一下单
    @RequestMapping(value="unifiedorder", method = RequestMethod.POST)//,produces = {"application/xml;charset=UTF-8"}
    @ResponseBody
    public void   getPay(@RequestParam Map<String, String> map , HttpServletResponse resp, @RequestBody  XmlRequest xmlRequest,PrintWriter out) throws Exception {
      String  resstr= xmlRequest.toString();
        logger.info("XmlRequest:{}", resstr); //@RequestBody XmlRequest xmlRequest,,  PrintWriter out
        Map<String,String> map1=MapJson.Json2Map(resstr);
        logger.info(map1.toString());
        if("JSAPI".equals(map1.get("trade_type"))){
            wechatJSServer.Pay(map1,out,resp);
        }else {
            out.print("Not support");
        }



//        payXmlResponse response = new payXmlResponse("1", "success","dfe","415");
//        return toxmlUtil.ojbectToXmlWithCDATA(payXmlResponse.class, response);




    }

    @RequestMapping("/xmlOrJson")
    @ResponseBody
    public Map<String, Object> xmlOrJson() {
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("list", employeeService.list());
        return map;
    }

//
//    @RequestMapping(value="unifiedorder", method = RequestMethod.POST)
//    public void pay(HttpServletRequest  req, HttpServletResponse resp) {
//        logger.info(req.toString());
//        logger.info("支付请求...");
//        SortedMap<String, String> map = XmlUtils.getParameterMap(req);
//        logger.info(map.toString());
//
//    }

    //订单查询
    @RequestMapping(value="orderquery", method = RequestMethod.POST)
    public void orderquery(@RequestParam(value = "appid") String appid,@RequestParam(value = "mch_id") String mch_id,@RequestParam Map<String, String> map , HttpServletResponse resp, PrintWriter out){
        out.println(map);


    }



    //关闭订单
    @RequestMapping(value="closeorder", method = RequestMethod.POST)
    public void closeorder(@RequestParam(value = "appid") String appid,@RequestParam(value = "mch_id") String mch_id,@RequestParam Map<String, String> map , HttpServletResponse resp, PrintWriter out){
        out.println(map);


    }


    //退款查询
    @RequestMapping(value="refundquery", method = RequestMethod.POST)
    public void refundquery(@RequestParam(value = "appid") String appid,@RequestParam(value = "mch_id") String mch_id,@RequestParam Map<String, String> map , HttpServletResponse resp, PrintWriter out){
        out.println(map);


    }



    //对账单下载
    @RequestMapping(value="downloadbill", method = RequestMethod.POST)
    public void downloadbill(@RequestParam(value = "appid") String appid,@RequestParam(value = "mch_id") String mch_id,@RequestParam Map<String, String> map , HttpServletResponse resp, PrintWriter out){
        out.println(map);


    }


    //下载资金账单
    @RequestMapping(value="downloadfundflow", method = RequestMethod.POST)
    public void downloadfundflow(@RequestParam(value = "appid") String appid,@RequestParam(value = "mch_id") String mch_id,@RequestParam Map<String, String> map , HttpServletResponse resp, PrintWriter out){
        out.println(map);


    }






}
