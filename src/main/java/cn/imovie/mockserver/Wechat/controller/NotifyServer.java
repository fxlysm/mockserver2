package cn.imovie.mockserver.Wechat.controller;

import cn.imovie.mockserver.Wechat.server.WechatJSServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Map;

@Api("支付通知")
@RestController
@RequestMapping(value = "pay")
public class NotifyServer {
    private static final Logger logger = LoggerFactory.getLogger(NotifyServer.class);
    @ApiOperation("模拟客户端接收支付通知")
    @ResponseBody
    @RequestMapping(value = "/handlerNotify", method = RequestMethod.POST)
    public void payNotify(@RequestParam Map<String, String> map, PrintWriter out , HttpServletRequest request) throws Exception {
        logger.info("接收到通知");
        out.print("SUCCESS");
    }
}
