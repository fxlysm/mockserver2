package cn.imovie.mockserver.Alipay.server;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public interface RefundServer {
    void RefundPay(Map map, HttpServletResponse resp) throws Exception;
    void RefundQuery(Map map,HttpServletResponse resp) throws Exception;
}
