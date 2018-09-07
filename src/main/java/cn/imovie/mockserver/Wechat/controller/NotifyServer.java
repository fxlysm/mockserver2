package cn.imovie.mockserver.Wechat.controller;

import cn.imovie.mockserver.Wechat.server.WechatJSServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@RequestMapping(value = "pay")
public class NotifyServer {
    private static final Logger logger = LoggerFactory.getLogger(NotifyServer.class);
    @ResponseBody
    @RequestMapping(value = "/handlerNotify", method = RequestMethod.POST)
    public void payNotify(@RequestParam Map<String, String> map, PrintWriter out , HttpServletRequest request) throws Exception {
        logger.info("接收到通知");
        out.print("SUCCESS");
    }
}
