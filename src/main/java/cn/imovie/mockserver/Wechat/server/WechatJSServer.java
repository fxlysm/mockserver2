package cn.imovie.mockserver.Wechat.server;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public interface WechatJSServer {
    public void Pay(Map map, PrintWriter out, HttpServletResponse resp) throws IOException;
}
