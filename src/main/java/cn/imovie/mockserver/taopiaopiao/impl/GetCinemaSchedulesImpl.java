package cn.imovie.mockserver.taopiaopiao.impl;

import cn.imovie.mockserver.taopiaopiao.controller.GetCinemaSchedules;
import cn.imovie.mockserver.util.DateUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class GetCinemaSchedulesImpl implements GetCinemaSchedules {
    private static final Logger logger = LoggerFactory.getLogger(GetCinemaSchedulesImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void GetCinemaSchedules(Map map,PrintWriter out){

        if(map.containsKey("cinema_id")){
            String cinema_id=map.get("cinema_id").toString();

            String command="SELECT * FROM tpp_cinema_schedules t WHERE t.cinema_id='"+cinema_id+"' AND show_time> '"+ DateUtil.GetTodayTime()+"'";
            List<JSONObject> scheduleObjects = new ArrayList<JSONObject>();
            logger.info("Command:"+command);
            List rows=jdbcTemplate.queryForList(command);
            Iterator it = rows.iterator();
            while(it.hasNext()) {
                Map Schedulesmap = (Map) it.next();
//                logger.info("Schedulesmap:"+Schedulesmap);

                String show_date= String.valueOf(Schedulesmap.get("show_date"));//
                String show_time= String.valueOf(Schedulesmap.get("show_time"));//
                String section_id= String.valueOf(Schedulesmap.get("section_id"));
                String service_fee= String.valueOf(Schedulesmap.get("service_fee"));
                String price= String.valueOf(Schedulesmap.get("price"));
                String hall_name= String.valueOf(Schedulesmap.get("hall_name"));
                String show_id= String.valueOf(Schedulesmap.get("show_id"));//

//            String is_expired= String.valueOf(Schedulesmap.get("is_expired"));
                String show_version= String.valueOf(Schedulesmap.get("show_version"));
                String id= String.valueOf(Schedulesmap.get("schedules_id"));//
                String close_time= String.valueOf(Schedulesmap.get("close_time"));
                String max_can_buy= String.valueOf(Schedulesmap.get("max_can_buy"));
                String schedule_area= "";
                boolean is_expired=false;

                JSONObject schedule = new JSONObject();
                schedule.put("section_id",section_id);
                schedule.put("service_fee",service_fee);
                schedule.put("price",price);
                schedule.put("show_date",show_date);
                schedule.put("hall_name",hall_name);
                schedule.put("show_id",show_id);
                schedule.put("is_expired",is_expired);
                schedule.put("show_version",show_version);
                schedule.put("cinema_id",cinema_id);
                schedule.put("show_time",show_time);
                schedule.put("id",id);
                schedule.put("close_time",close_time);
                schedule.put("max_can_buy",max_can_buy);
                schedule.put("schedule_area",schedule_area);
                scheduleObjects.add(schedule);
            }

            JSONObject schedulelist = new JSONObject();
            schedulelist.put("schedule",scheduleObjects);

            JSONObject schedules = new JSONObject();
            schedules.put("schedules",schedulelist);

            JSONObject return_value = new JSONObject();
            return_value.put("return_value",schedules);
            return_value.put("return_code","0");
            return_value.put("return_message","success");

            JSONObject result = new JSONObject();
            result.put("result",return_value);

            JSONObject response = new JSONObject();
            response.put("film_data_third_party_schedules_get_response",result);
            logger.info("response:"+response);
            out.println(response);
        }else {
            out.println("cinema_id not null");
        }

    }
}
