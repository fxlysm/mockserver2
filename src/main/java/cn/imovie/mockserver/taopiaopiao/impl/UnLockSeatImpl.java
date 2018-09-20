package cn.imovie.mockserver.taopiaopiao.impl;

import cn.imovie.mockserver.taopiaopiao.controller.UnLockSeat;
import cn.imovie.mockserver.util.ErrorUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

@Service
public class UnLockSeatImpl implements UnLockSeat {


    private static final Logger logger = LoggerFactory.getLogger(UnLockSeatImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void UnLockSeat(Map map, PrintWriter out){


        if (map.containsKey("lock_seat_apply_key")&&map.containsKey("user_id")&&map.containsKey("ext_user_id")){
            String user_id=map.get("user_id").toString();
            String lock_seat_apply_key=map.get("lock_seat_apply_key").toString();
            String ext_user_id=map.get("ext_user_id").toString();
            List rows =  jdbcTemplate.queryForList("SELECT * FROM tpp_translog WHERE apply_key='"+lock_seat_apply_key+"'AND order_status !='3'");
            if(rows.size()>0){
                Map ordermap = jdbcTemplate.queryForMap("SELECT * FROM tpp_translog WHERE  apply_key='"+lock_seat_apply_key+"'");

                if(ordermap.get("order_status").toString().equals("0")){//0 锁座未支付
                  String  seat_ids=ordermap.get("seat_ids").toString();
                    String[] aa = seat_ids.split("\\|");
                    for (int m=0;m<aa.length;m++) {
                        String seat_id = aa[m];
                        String commad="UPDATE tpp_cinema_schedule_seats SET islocked='0',seat_status='1',locktime='0'"+"WHERE seat_id='"+seat_id+"'";
                        logger.info("Command:"+commad);
                        jdbcTemplate.execute(commad);

                        String commad2="UPDATE tpp_translog SET order_status='3' WHERE apply_key='"+lock_seat_apply_key+"'";
                        logger.info("Command:"+commad2);
                        jdbcTemplate.execute(commad2);
                    }

                    JSONObject seat_response=new JSONObject();
                    seat_response.put("return_code","0");
                    seat_response.put("return_value",true);
                    seat_response.put("return_message","解锁成功");
                    JSONObject response=new JSONObject();
                    response.put("film_data_third_party_unlock_seat_response",seat_response);
                    out.println(response);


                }else {
                    ErrorUtil.prserror("非法参数",50,"isv.invalid-parameter","Remote service error",out);
                }

            }else {
                ErrorUtil.prserror("非法参数",50,"isv.invalid-parameter","Remote service error",out);
            }

        }else {

            ErrorUtil.prserror("非法参数",50,"isv.invalid-parameter","Remote service error",out);
        }


    }


}
