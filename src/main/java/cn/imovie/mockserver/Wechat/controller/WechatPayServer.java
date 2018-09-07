package cn.imovie.mockserver.Wechat.controller;

import cn.imovie.mockserver.Wechat.co.RefoundRes;
import cn.imovie.mockserver.Wechat.co.payXmlResponse;
import cn.imovie.mockserver.Wechat.server.WechatJSServer;
import cn.imovie.mockserver.Wechat.co.XmlRequest;
import cn.imovie.mockserver.Wechat.server.orderqueryServer;
import cn.imovie.mockserver.util.MapJson;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@RequestMapping(value="pay")
@Api("微信支付api")
public class WechatPayServer {

    @Autowired
    private WechatJSServer wechatJSServer;

    @Autowired
    private orderqueryServer orderquery;

    private static final Logger logger = LoggerFactory.getLogger(WechatPayServer.class);
//    统一下单
@ApiOperation("统一下单【入参为xml格式，这里调试使用json格式】")
//@ApiImplicitParams({
//        @ApiImplicitParam(paramType="header",name="username",dataType="String",required=true,value="用户的姓名",defaultValue="zhaojigang"),
//        @ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="用户的密码",defaultValue="wangna")
//})
//@ApiResponses({
//        @ApiResponse(code=400,message="请求参数没填好"),
//        @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
//})
    @RequestMapping(value="unifiedorder", method = RequestMethod.POST,produces = MediaType.APPLICATION_XML_VALUE)//,produces = {"application/xml;charset=UTF-8"}
    @ResponseBody
    public void getPay(HttpServletResponse resp, @Valid @RequestBody  XmlRequest xmlRequest, BindingResult result) throws Exception {
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
//                out.print(error.getDefaultMessage());, PrintWriter out
                resp.getWriter().write(error.getDefaultMessage());
            }
        }else {

            String  resstr= xmlRequest.toString();
            logger.info("**************收到微信JS支付请求*****************");
            logger.info("XmlRequest:{}", resstr); //@RequestBody XmlRequest xmlRequest,,  PrintWriter out
            Map<String,String> map1=MapJson.Json2Map(resstr);
            logger.info(map1.toString());
            if("JSAPI".equals(map1.get("trade_type"))){

                wechatJSServer.Pay(map1,resp);
            }
            else {

                resp.getWriter().write("Not support");
                        }

            logger.info("**************完成微信JS支付请求*****************");

//        payXmlResponse response = new payXmlResponse("1", "success","dfe","415");
//        return toxmlUtil.ojbectToXmlWithCDATA(payXmlResponse.class, response);

        }


//        throw new Exception("发生错误");


}


    //
    @ApiOperation("订单查询")
    @RequestMapping(value="orderquery", method = RequestMethod.POST)
    public void orderquery( @RequestBody  XmlRequest xmlRequest ,HttpServletResponse resp)throws IOException {
        String  resstr= xmlRequest.toString();
        logger.info("**************收到微信订单查询请求*****************");
        logger.info("XmlRequest:{}", resstr); //@RequestBody XmlRequest xmlRequest,,  PrintWriter out
        Map<String,String> map1=MapJson.Json2Map(resstr);
        logger.info(map1.toString());
        orderquery.Transactionstatusquery(map1,resp);

    }



    //关闭订单
    @ApiOperation("关闭订单")
    @RequestMapping(value="closeorder", method = RequestMethod.POST)
    public void closeorder(@RequestParam(value = "appid") String appid,@RequestParam(value = "mch_id") String mch_id,@RequestParam(value = "out_trade_no") String out_trade_no,HttpServletResponse resp, PrintWriter out){


    }


    //退款查询
    @ApiOperation("退款查询")
    @RequestMapping(value="refundquery", method = RequestMethod.POST)
    public void refundquery(HttpServletResponse resp,@Valid @RequestBody RefoundRes refoundRes, BindingResult result)throws Exception {
//        out.println(map);
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {

                resp.getWriter().write(error.getDefaultMessage());
            }
        }else {

            String resstr = refoundRes.toString();
            logger.info("**************收到微信退款查询请求*****************");
            logger.info("XmlRequest:{}", resstr); //@RequestBody XmlRequest xmlRequest,,  PrintWriter out
            Map<String, String> map1 = MapJson.Json2Map(resstr);
            logger.info(map1.toString());


        }

    }



    //对账单下载
    @ApiOperation("对账单下载")
    @RequestMapping(value="downloadbill", method = RequestMethod.POST)
    public void downloadbill(@RequestParam(value = "appid") String appid,@RequestParam(value = "mch_id") String mch_id,@RequestParam Map<String, String> map , HttpServletResponse resp, PrintWriter out){
        out.println(map);


    }


    //下载资金账单
    @ApiOperation("下载资金账单")
    @RequestMapping(value="downloadfundflow", method = RequestMethod.POST)
    public void downloadfundflow(@RequestParam(value = "appid") String appid,@RequestParam(value = "mch_id") String mch_id,@RequestParam Map<String, String> map , HttpServletResponse resp, PrintWriter out){
        out.println(map);


    }






}
