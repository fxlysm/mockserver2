package cn.imovie.mockserver.taopiaopiao.impl;

import cn.imovie.mockserver.Wechat.util.StringUtil;
import cn.imovie.mockserver.taopiaopiao.controller.Order;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Service
public class OrderImpl implements Order {

    private static final Logger logger = LoggerFactory.getLogger(LockSeatImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void Order(Map map, PrintWriter out){
        String params;
        String user_id=map.get("user_id").toString();
        String lock_seat_apply_key=map.get("lock_seat_apply_key").toString();

        String ext_user_id=map.get("ext_user_id").toString();
        String ext_order_id=map.get("ext_order_id").toString();
        String total_price=map.get("total_price").toString();
        if(map.containsKey("params")){
            params=map.get("params").toString();
        }

        JSONObject return_value=new JSONObject();

        JSONObject result=new JSONObject();
        //更新状态到数据库
        List rows =  jdbcTemplate.queryForList("SELECT * FROM tpp_translog WHERE apply_key='"+lock_seat_apply_key+"'  AND order_status !='3'");
        if(rows.size()>0){
            Map ordermap = jdbcTemplate.queryForMap("SELECT * FROM tpp_translog WHERE  apply_key='"+lock_seat_apply_key+"'");
            String tb_order_id=ordermap.get("transId").toString();
//            String ticket_contents=ordermap.get("ticket_contents").toString();
            String order_status=ordermap.get("order_status").toString();
            if(order_status.equals("2")){
                result.put("return_code","1");
                result.put("return_message","非法参数");
            }else {
                String ticket_contents="取票码："+ StringUtil.getCode(8,0);
                String commad="UPDATE tpp_translog SET tb_order_id='"+ext_order_id+"',ticket_contents='"+ticket_contents+"',order_status='2' ,total_price='"+total_price+"' "+"WHERE apply_key='"+lock_seat_apply_key+"'";
                logger.info("Command:"+commad);
                jdbcTemplate.execute(commad);

                String status="TRADE_SUCCESS";
                String message="出票成功";

                return_value.put("tb_order_id",tb_order_id);
                return_value.put("ticket_contents",ticket_contents);
                return_value.put("status",status);
                return_value.put("message",message);

                result.put("return_code","0");
                result.put("return_value",return_value);
                result.put("return_message","success");

            }



        }else {
            result.put("return_code","1");
            result.put("return_message","非法参数");
        }

        JSONObject order_response=new JSONObject();
        order_response.put("film_data_third_party_issue_order_response",result);




        out.println(order_response);

    }
}
