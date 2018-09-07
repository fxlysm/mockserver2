package cn.imovie.mockserver.Wechat.server;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public interface orderqueryServer {
    public void  Transactionstatusquery(Map<String, String> map, HttpServletResponse resp)throws IOException ;
}
