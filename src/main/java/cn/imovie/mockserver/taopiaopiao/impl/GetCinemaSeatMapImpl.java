package cn.imovie.mockserver.taopiaopiao.impl;

import cn.imovie.mockserver.action.TPPSchedulesSeat;
import cn.imovie.mockserver.taopiaopiao.controller.GetCinemaSeatMap;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class GetCinemaSeatMapImpl implements GetCinemaSeatMap {

    private static final Logger logger = LoggerFactory.getLogger(GetCinemaSeatMapImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${user.refound.time}")
    private int min_user_change_time;

    public void GetCinemaSeatMap(Map map, PrintWriter out){

        String user_id=map.get("user_id").toString();
        String schedule_id=map.get("schedule_id").toString();

        String schedulescomand="SELECT * FROM tpp_cinema_schedule_seats  WHERE schedules_id='"+schedule_id+"'";


        List schedulesrows = jdbcTemplate.queryForList(schedulescomand);
        int seat_count= schedulesrows.size();
        logger.info("排期座位数："+seat_count);
        Iterator scheduleslist = schedulesrows.iterator();


        List<JSONObject> seatsObjects = new ArrayList<JSONObject>();

        while(scheduleslist.hasNext()){
            Map schedules_map = (Map) scheduleslist.next();
            logger.info("MAP："+schedules_map);
            JSONObject seat = new JSONObject();

            seat.put("status",Integer.parseInt(schedules_map.get("seat_status").toString()));
            seat.put("flag",Integer.parseInt(schedules_map.get("flag").toString()));
            seat.put("top_px",Integer.parseInt(schedules_map.get("top_px").toString()));
            seat.put("left_px",Integer.parseInt(schedules_map.get("left_px").toString()));
            seat.put("column",Integer.parseInt(schedules_map.get("seat_column").toString()));
            seat.put("row",Integer.parseInt(schedules_map.get("seat_row").toString()));
            seat.put("row_name",schedules_map.get("row_name"));
            seat.put("name",schedules_map.get("seat_name"));
            seat.put("ext_id",schedules_map.get("ext_id"));
            seat.put("area",schedules_map.get("area"));

            seatsObjects.add(seat);

        }
        Map halldetal = jdbcTemplate.queryForMap("select * from tpp_cinema_schedules s ,tpp_cinema_hall h where s.schedules_id='"+schedule_id+"' and s.hall_id=h.hall_id");

        logger.info("通过排期查询影厅MAP："+halldetal);

        Map count = jdbcTemplate.queryForMap("select seat_status,count(*) from tpp_cinema_schedule_seats where seat_status='0' group by seat_status; ");
//        logger.info("count："+count);
        int sold_count= Integer.parseInt(count.get("count(*)").toString());

        JSONObject seat = new JSONObject();
        seat.put("seat",seatsObjects);

        JSONObject seats = new JSONObject();
        boolean regular;
        seats.put("seats",seat);
        if(halldetal.get("regular").toString().equals("1")||halldetal.get("regular").toString().equals("ture")){
            regular=true;
        }else {
            regular=false;
        }

        seats.put("sold_count",sold_count);
        seats.put("seat_count",seat_count);
        seats.put("max_row",Integer.parseInt(halldetal.get("max_row").toString()));
        seats.put("min_row",Integer.parseInt(halldetal.get("min_row").toString()));
        seats.put("max_column",Integer.parseInt(halldetal.get("max_column").toString()));
        seats.put("min_column",Integer.parseInt(halldetal.get("min_column").toString()));
        seats.put("max_top_px",Integer.parseInt(halldetal.get("max_top_px").toString()));
        seats.put("min_top_px",Integer.parseInt(halldetal.get("min_top_px").toString()));
        seats.put("max_left_px",Integer.parseInt(halldetal.get("max_left_px").toString()));
        seats.put("min_left_px",Integer.parseInt(halldetal.get("min_left_px").toString()));
        seats.put("regular",regular);
        seats.put("max_can_buy",Integer.parseInt(halldetal.get("max_can_buy").toString()));
        seats.put("notice","最多只能选取"+halldetal.get("max_can_buy")+"个座位");

        JSONObject cinema=new JSONObject();
        cinema.put("min_time_line",min_user_change_time);
        cinema.put("total_charge",0);

        List<JSONObject> cinemalist = new ArrayList<JSONObject>();
        cinemalist.add(cinema);

        JSONObject charge_rule=new JSONObject();
        charge_rule.put("top_charge_rule",cinemalist);

        JSONObject charge_rules=new JSONObject();
        charge_rules.put("charge_rules",charge_rule);
        charge_rule.put("min_user_change_time",min_user_change_time);


        seats.put("refund_rule",charge_rule);

        JSONObject return_value = new JSONObject();
        return_value.put("return_value",seats);
        return_value.put("return_code","0");
        return_value.put("return_message","加载成功");

        JSONObject result = new JSONObject();
        result.put("result",return_value);

        JSONObject map_response = new JSONObject();
        map_response.put("film_data_third_party_seat_map_response",result);

        out.println(map_response);
    }
}
