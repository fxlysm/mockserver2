package cn.imovie.mockserver.Wechat.impl;

import cn.imovie.mockserver.Wechat.server.wechatClosePay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Service
public class WechatJsCloseorder  implements wechatClosePay {
    private static final Logger logger = LoggerFactory.getLogger(WechatJsCloseorder.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${pay.wechat.key}")
    private String Signkey;

    @Override
    public void  closePay(Map<String, String> map, HttpServletResponse resp)  throws IOException {

    }

}
