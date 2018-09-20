package cn.imovie.mockserver.Wechat.server;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface wechatClosePay {
    void  closePay(Map<String, String> map, HttpServletResponse resp)  throws IOException;
}
