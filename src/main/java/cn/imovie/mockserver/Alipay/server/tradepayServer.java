package cn.imovie.mockserver.Alipay.server;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public interface tradepayServer {
     void Pay(Map map, HttpServletResponse resp) throws Exception;
     void PrecreatePay(Map map,HttpServletResponse resp) throws Exception;
    void CreatePay(Map map,HttpServletResponse resp) throws Exception;
    void Query(Map map,HttpServletResponse resp) throws Exception;
}
