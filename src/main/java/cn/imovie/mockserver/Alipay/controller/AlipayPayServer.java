package cn.imovie.mockserver.Alipay.controller;


import cn.imovie.mockserver.Alipay.co.AlipayPay;
import cn.imovie.mockserver.Alipay.server.RefundServer;
import cn.imovie.mockserver.Alipay.server.tradepayServer;
import cn.imovie.mockserver.util.MapJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "")
@Api("支付宝支付")
public class AlipayPayServer {

    @Autowired
    private tradepayServer tradepay;

    @Autowired
    private RefundServer refundServer;

    private static final Logger logger = LoggerFactory.getLogger(AlipayPayServer.class);
    @ApiOperation("支付宝支付交易相关接口")
    @RequestMapping(value="gateway.do", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void   getPay(@RequestParam Map<String, String> map ,@Valid  @RequestBody AlipayPay alipayp,  BindingResult bindingResult,HttpServletResponse resp) throws Exception {
        if(bindingResult.hasErrors()){
            for (ObjectError error : bindingResult.getAllErrors()) {
                resp.getWriter().write(error.getDefaultMessage());
            }
        }else {


            AlipayPay alipay=new AlipayPay();
        logger.info("接收到参数：\n"+map.toString());
        String biz_content= alipay.getBiz_content();//map.get("biz_content");
            System.out.println("content"+biz_content);
        String method=map.get("method");
//        String app_id=map.get("app_id");
//        String sign_type=map.get("sign_type");
//        String charset=map.get("charset");
//        String version=map.get("version");

        logger.info("biz_content:"+biz_content);

        if("alipay.trade.pay".equals(method)){
            logger.info("**************** 统一收单交易支付********************");
            tradepay.Pay(map,resp);
//            Map<String,String> reqmap=MapJson.Json2Map(biz_content);
//            logger.info("reqmap:"+reqmap);

        }else if("alipay.trade.precreate".equals(method)){
            logger.info("**************** 统一收单线下交易预创建********************");
            tradepay.PrecreatePay(map,resp);

        }else if("alipay.trade.create".equals(method)){
            logger.info("**************** 统一收单交易创建********************");
            tradepay.CreatePay(map,resp);
        }else if("alipay.trade.query".equals(method)){
            logger.info("**************** 统一收单线下交易查询********************");
            tradepay.Query(map,resp);

        }else if("alipay.trade.refund".equals(method)) {
            logger.info("**************** 统一收单交易退款********************");
            refundServer.RefundPay(map,resp);
        }else if("alipay.trade.fastpay.refund.query".equals(method)) {
            logger.info("**************** 统一收单交易退款********************");
            refundServer.RefundQuery(map,resp);
        }
        else {
//            out.print("not support");
        }

        }
    }



//    @RequestMapping(value ="/demo", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
//    public void demo2(@RequestBody @Valid AlipayPay params, BindingResult result,@RequestParam Map<String, String> map,HttpServletResponse response, PrintWriter out )throws Exception{
//        if(result.hasErrors()){
//            for (ObjectError error : result.getAllErrors()) {
//                response.setCharacterEncoding("UTF-8"); //设置编码格式
//                response.setContentType("text/html");   //设置数据格式
//                out=response.getWriter();
//                out.write(error.getDefaultMessage());
//            }
//        }else {
//            out.print(map.toString());
//        }
//    }
}
