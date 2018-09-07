package cn.imovie.mockserver.notice;


import cn.imovie.mockserver.sign.RSA;

import cn.imovie.mockserver.util.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@RequestMapping(value = "/payCenterApi")
public class NotifyController {
    private static final Logger logger = LoggerFactory.getLogger(NotifyController.class);


//    @Value("${platform.public_key}")
    private String public_key="123456";

    @ResponseBody
    @RequestMapping(value = "/handlerNotify", method = RequestMethod.POST)
    public void payNotify(@RequestParam Map<String, String> map, PrintWriter out , HttpServletRequest request) throws Exception {

        logger.info("\n\n");
        logger.info("============================接收到回调通知===========================");

        logger.info("接收到的request："+request.getParameter("cpId"));
        logger.info("接收到的通知参数：\n" + map);

        String cpId=map.get("cpId");
        logger.info("接收到的cpId:"+cpId);

        String   getsign=map.get("sign");
        logger.info("接收到的Sign:"+getsign);



        Map<String, String> params = SignUtils.paraFilter(map);
        if (params.containsKey("sign"))
            params.remove("sign");
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        logger.info("预签名字串:"+preStr);




//        Map<String, String> ff = MySqlUtil.GetValue(cpId);
//					if ("RSA".equals(ff.get("sign_type"))) {
        if("RSA".equals(map.get("signType"))){
            logger.info("检测到是RAS验签，正在使用平台公钥验签...");
            if(RSA.verifyRSA256(preStr, getsign,public_key,"utf-8")){
                out.print("SUCCESS");
                logger.info("签名验证结果： SUCCESS");
            }else {
                out.print("FAIL");
                logger.info("签名验证结果：FAIL");
            }

        }else {
            out.print("FAIL");
            logger.info("签名验证结果：FAIL");
        }



        logger.info("====================回调通知结束===========================\n");
        logger.info("\n\n");

    }



}
