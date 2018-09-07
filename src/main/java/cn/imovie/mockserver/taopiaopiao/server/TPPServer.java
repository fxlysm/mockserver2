package cn.imovie.mockserver.taopiaopiao.server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.Map;

@RestController
//
@RequestMapping(value = "/router")
public class TPPServer {
    private static final Logger logger = LoggerFactory.getLogger(TPPServer.class);

    @ResponseBody
    @RequestMapping(value = "/rest", method = RequestMethod.POST)
    public void getPay(@RequestParam Map<String, String> map , PrintWriter out){
        logger.debug("MAP:"+map);
        logger.debug("************接收到XX**********************");
        String service=map.get("service");



    }
//
//    @ResponseBody
//    @RequestMapping(value = "/refund", method = RequestMethod.POST)
//    public void getrefund(@RequestParam Map<String, String> map , PrintWriter out){
//        logger.debug("MAP:"+map);
//        logger.debug("************接收XX*********************");
//        String service=map.get("service");
//
//
//
//    }
}
