package cn.imovie.mockserver.taopiaopiao.impl;

import cn.imovie.mockserver.taopiaopiao.controller.GetCinemas;
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
public class GetCinemaImpl implements GetCinemas {


    private static final Logger logger = LoggerFactory.getLogger(GetCinemaImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private boolean support_third_party_refund;


    public void Getcinemas(Map map, PrintWriter out){


        List<JSONObject> mtop_cinemaObjects = new ArrayList<JSONObject>();

        String command="SELECT * FROM tpp_cinemaslist where support_third_party_refund =1";
        logger.info("Mysql Command:"+command);
        List rows = jdbcTemplate.queryForList(command);
       int total=rows.size();
        logger.info("地区数："+total);
        Iterator it = rows.iterator();
        while(it.hasNext()) {
            Map region_map = (Map) it.next();
            JSONObject mtop_cinema = new JSONObject();
            String city_id = String.valueOf(region_map.get("city_id"));//
            String schedule_close_time = String.valueOf(region_map.get("schedule_close_time"));//
            String latitude = String.valueOf(region_map.get("latitude"));//
            String longitude = String.valueOf(region_map.get("longitude"));//
            String region_name = String.valueOf(region_map.get("region_name"));//

            String cinema_name = String.valueOf(region_map.get("cinema_name"));//
            String address = String.valueOf(region_map.get("address"));//
            String id = String.valueOf(region_map.get("cinema_id"));//
            String phone = String.valueOf(region_map.get("phone"));//
            String standard_id = String.valueOf(region_map.get("standard_id"));//

            int is_support= (int) region_map.get("support_third_party_refund");
            if(is_support==1){
                support_third_party_refund=true;
            }else {
                support_third_party_refund=false;
            }


            mtop_cinema.put("city_id", city_id);
            mtop_cinema.put("schedule_close_time", schedule_close_time);
            mtop_cinema.put("region_name", region_name);
            mtop_cinema.put("latitude", latitude);
            mtop_cinema.put("longitude", longitude);
            mtop_cinema.put("cinema_name", cinema_name);
            mtop_cinema.put("address", address);
            mtop_cinema.put("phone", phone);
            mtop_cinema.put("standard_id", standard_id);
            mtop_cinema.put("id", id);
            mtop_cinema.put("support_third_party_refund",support_third_party_refund);

            mtop_cinemaObjects.add(mtop_cinema);


        }
        JSONObject mtop_cinema2 = new JSONObject();
        mtop_cinema2.put("mtop_cinema",mtop_cinemaObjects);

        JSONObject mtop_cinemas = new JSONObject();
        mtop_cinemas.put("mtop_cinemas",mtop_cinema2);
        mtop_cinemas.put("total_count",total);

        JSONObject return_value = new JSONObject();
        return_value.put("return_value",mtop_cinemas);
        return_value.put("return_code","0");
        return_value.put("return_message","success");

        JSONObject result = new JSONObject();
        result.put("result",return_value);

        JSONObject film_data_third_party_regions_get_response = new JSONObject();
        film_data_third_party_regions_get_response.put("film_data_third_party_regions_get_response",result);
        logger.info(film_data_third_party_regions_get_response.toString());
//        JSONObject result = new JSONObject();
//        result.put("filmname","我不是药神");
        out.println(film_data_third_party_regions_get_response);
    }
}
