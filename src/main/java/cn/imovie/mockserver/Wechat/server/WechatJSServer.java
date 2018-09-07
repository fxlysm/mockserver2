package cn.imovie.mockserver.Wechat.server;

import cn.imovie.mockserver.Wechat.co.payXmlResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface WechatJSServer {
    void Pay(Map map, HttpServletResponse resp) throws IOException;
}
