package cn.imovie.mockserver.taopiaopiao.server;


import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Controller
//@Api("服务前端显示api")
public class IndexServer {
    private static final Logger logger = LoggerFactory.getLogger(IndexServer.class);
//    @RequestMapping("/hello")
//    public String helloHtml(HashMap<String, Object> map) {
//        map.put("hello", "欢迎进入HTML页面");
//        return "/index2";
//    }

//    @Controller
    public class HtmlController {
        @GetMapping("/index")
        public String html() {
            return "/index.html";

        }

    }
//    @RequestMapping("/test")
//    public  void jsonstr(@RequestBody(required=true) Map<String,Object> map,PrintWriter out){
//        logger.info(map.toString());
//
//        out.print(map.toString());
//    }


    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String index(){
        return "home";
    }
}
