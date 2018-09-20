package cn.imovie.mockserver.taopiaopiao.impl;

import cn.imovie.mockserver.Wechat.util.StringUtil;
import cn.imovie.mockserver.exception.MyException;
import cn.imovie.mockserver.taopiaopiao.controller.LockSeat;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class LockSeatImpl implements LockSeat {

    /***
     * 将座位标记为锁定状态
     */


    private static final Logger logger = LoggerFactory.getLogger(LockSeatImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${tpp.appKey}")
    private String appKey;

//    @Value("${lock.return_code}")
//    private String return_code;

    public void LockSeat(Map map, PrintWriter out){

        String com="SELECT value FROM tpp_sys_config c WHERE c.`key`='lock.seat.return_code'";
        String return_code=jdbcTemplate.queryForObject(com, String.class);

      logger.info("return_code:"+return_code);

//        if(map.containsKey("schedule_id")&&map.containsKey("seat_ids")&&map.containsKey("seat_names")&&map.containsKey("mobile")&&map.containsKey("ext_user_id")){
            String schedule_id=map.get("schedule_id").toString();
            String user_id=map.get("user_id").toString();
            String seat_ids=map.get("seat_ids").toString();
            String seat_names=map.get("seat_names").toString();
            String mobile=map.get("mobile").toString();
            String ext_user_id=map.get("ext_user_id").toString();
            String apply_key= StringUtil.UuId();
            String transId="28"+StringUtil.getStringDate("yyMMddHHmmss")+StringUtil.getCode(8,0);;

        JSONObject return_value =new JSONObject();
        JSONObject result =new JSONObject();
            if(return_code.equals("0")){


                long locktime=System.currentTimeMillis();
                return_value.put("lock_time",locktime);
                return_value.put("status","LOCK_SUCCESS");
                return_value.put("apply_key",apply_key);
                return_value.put("default_lock_second",900);

                result.put("return_code",return_code);
                result.put("return_value",return_value);
                result.put("return_message","success");
                String order_status="0";

                //****************************更改状态及记录
                String[] aa = seat_ids.split("\\|");
                for (int m=0;m<aa.length;m++){
                   String seat_id=aa[m];
                   String commad="UPDATE tpp_cinema_schedule_seats SET islocked='1',locktime='"+locktime+"'"+"WHERE seat_id='"+seat_id+"'";
                   logger.info("Command:"+commad);
                    jdbcTemplate.execute(commad);
                }

                //創建订单记录
                String command="INSERT INTO tpp_translog(transId,user_id,schedule_id,apply_key,seat_ids,mobile,ext_user_id,order_status)value ('"+transId+"','"+user_id+"','"+schedule_id+"','"+apply_key+"','"+seat_ids+"','"+mobile+"','"+ext_user_id+"','"+order_status+"')";
                logger.info("正在插入订单记录");
                logger.info("MySql:"+command);
                jdbcTemplate.execute(command);


            }else {
                result.put("return_code",return_code);

                result.put("return_message","fail");
            }


        JSONObject results=new JSONObject();
        results.put("result",result);

        JSONObject res=new JSONObject();
        res.put("film_data_third_party_lock_seat_response",results);

        out.println(res);

//            String schedulescomand="SELECT * FROM tpp_cinema_schedule_seats  WHERE schedules_id='"+schedule_id+"'";
//
//
//            List schedulesrows = jdbcTemplate.queryForList(schedulescomand);
//            int seat_count= schedulesrows.size();
//            logger.info("排期座位数："+seat_count);
//            Iterator scheduleslist = schedulesrows.iterator();
//
//            while(scheduleslist.hasNext()){
//
//
//
//            }
//        }


    }
}
