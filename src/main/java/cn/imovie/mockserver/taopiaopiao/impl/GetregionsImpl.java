package cn.imovie.mockserver.taopiaopiao.impl;

import cn.imovie.mockserver.taopiaopiao.controller.Getregions;
import cn.imovie.mockserver.taopiaopiao.server.TPPServer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class GetregionsImpl implements Getregions {
    private static final Logger logger = LoggerFactory.getLogger(GetregionsImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void Getregions(HttpServletResponse resp, Map map)throws IOException {

        List<JSONObject> regionObjects = new ArrayList<JSONObject>();

        JSONObject regionsObject = new JSONObject();

//        region.put("region","");


        String command="SELECT * FROM tpp_bas_area where parent_area_id is not null ";
        logger.info("Mysql Command:"+command);
        List rows = jdbcTemplate.queryForList(command);
        logger.info("地区数："+rows.size());
        Iterator it = rows.iterator();
        while(it.hasNext()) {
            Map region_map = (Map) it.next();
            JSONObject region = new JSONObject();
            String pin_yin=String.valueOf(region_map.get("area_pinyin"));//
            String city_code=String.valueOf(region_map.get("area_code"));//
            String region_name=String.valueOf(region_map.get("area_full_name"));//
            String parent_id=String.valueOf(region_map.get("parent_area_id"));//
            String id=String.valueOf(region_map.get("area_id"));//

            region.put("pin_yin",pin_yin);
            region.put("city_code",city_code);
            region.put("region_name",region_name);
            region.put("parent_id",parent_id);
            region.put("id",id);
            regionObjects.add(region);
        }

        regionsObject.put("region",regionObjects);



//        System.out.println(regionsObject.toString());
//        logger.info(regionsObject.toString());
        JSONObject regions = new JSONObject();
        regions.put("regions",regionsObject);
//        logger.info(regions.toString());


        JSONObject return_value = new JSONObject();
        return_value.put("return_value",regions);
        return_value.put("return_code","0");
        return_value.put("return_message","success");
//        logger.info(return_value.toString());

        JSONObject result = new JSONObject();
        result.put("result",return_value);

//        logger.info(result.toString());

        JSONObject film_data_third_party_regions_get_response = new JSONObject();
        film_data_third_party_regions_get_response.put("film_data_third_party_regions_get_response",result);
        logger.info(film_data_third_party_regions_get_response.toString());

        resp.setHeader("Content-type", "application/json;charset=utf-8");
        resp.getWriter().write(film_data_third_party_regions_get_response.toString());

//        out.println(film_data_third_party_regions_get_response);

    }


    public static void main(String[] args){
        dd();
    }

    public static void dd(){

        /**
         * {
         　　　　　　"name":"王尼玛",
         　　　　　　"fans":[{
         　　　　　　　　　　　　"name":"小王",
         　　　　　　　　　　　　"age":"7"
         　　　　　　　　　　　},{
         　　　　　　　　　　　　"name":"小尼玛",
         　　　　　　　　　　　　"age":"10"
         　　　　　　　　　　　}]
         　　　　　　}
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","王尼玛");

        //粉丝是个数组,其实就是嵌套json
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name","小王");
        jsonObject1.put("age",7);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name","小尼玛");
        jsonObject2.put("age",10);

        //从此处可以看出其实list和json也是互相转换的
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        jsonObjects.add(jsonObject1);
        jsonObjects.add(jsonObject2);
        jsonObject.put("fans",jsonObjects);

        System.out.println("jsonObject直接创建json:" + jsonObject);
    }
}
