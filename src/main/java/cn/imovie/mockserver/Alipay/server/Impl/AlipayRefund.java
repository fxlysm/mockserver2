package cn.imovie.mockserver.Alipay.server.Impl;

import cn.imovie.mockserver.Alipay.server.RefundServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Service
public class AlipayRefund implements RefundServer {

    private static final Logger logger = LoggerFactory.getLogger(AlipayRefund.class);

    public void RefundPay(Map map, HttpServletResponse resp) throws Exception{

    }

    public void RefundQuery(Map map,HttpServletResponse resp) throws Exception{

    }
}
