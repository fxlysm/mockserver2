package cn.imovie.mockserver.taopiaopiao.server;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexServer {

    @RequestMapping("/hello")
    public String helloHtml(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        return "/index2";
    }

    @Controller
    public class HtmlController {
        @GetMapping("/index")
        public String html() {
            return "/index.html";

        }

    }


    @RequestMapping("/")
    public String index(){
        return "home";
    }
}
