package cn.imovie.mockserver.taopiaopiao.impl;

import cn.imovie.mockserver.taopiaopiao.controller.Getsoonshows;
import cn.imovie.mockserver.util.DateUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.*;

@Service
public class GetsoonshowsImpl implements Getsoonshows {

    private static final Logger logger = LoggerFactory.getLogger(GethotshowsImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public void Getsoonshows(Map map, PrintWriter out){
        List<JSONObject> hot_filmObjects = new ArrayList<JSONObject>();
        Date d = new Date();

        String command="SELECT * FROM  tpp_film t where t.isplay='0' AND t.open_time >'"+ DateUtil.GetToday()+ "'";
        logger.info("Mysql Command:"+command);
        List rows = jdbcTemplate.queryForList(command);
        int total=rows.size();

        if(total>0){
            logger.info("热映数："+total);
            Iterator it = rows.iterator();
            while(it.hasNext()) {
                Map hotshows = (Map) it.next();
                logger.info("MAP:"+hotshows);
                JSONObject film = new JSONObject();
                String showid=String.valueOf(hotshows.get("show_id"));
                String show_name=String.valueOf(hotshows.get("show_name"));
                String show_name_en=String.valueOf(hotshows.get("show_name_en"));
                String director=String.valueOf(hotshows.get("director"));
                String leading_role=String.valueOf(hotshows.get("leading_role"));
                String filmtype=String.valueOf(hotshows.get("filmtype"));
                String country=String.valueOf(hotshows.get("country"));
                String language=String.valueOf(hotshows.get("language"));
                String duration=String.valueOf(hotshows.get("duration"));
                String open_day=String.valueOf(hotshows.get("open_day"));
                String remark=String.valueOf(hotshows.get("remark"));
                String description=String.valueOf(hotshows.get("description"));
                String open_time=String.valueOf(hotshows.get("open_time"));
                String poster=String.valueOf(hotshows.get("poster"));
                String background_picture=String.valueOf(hotshows.get("background_picture"));
                String trailer_list=String.valueOf(hotshows.get("trailer_list"));
                String show_version_list=String.valueOf(hotshows.get("show_version_list"));

                /***
                 *  以下部分为取 影片版本
                 */
                String command2="SELECT version_type FROM  tpp_film_version t where t.film_id='"+showid+"'";
                List rows2 = jdbcTemplate.queryForList(command2);
                Iterator it2 = rows2.iterator();
                List<String> ver=new LinkedList<String>();
                while(it2.hasNext()) {
                    Map<String,String> version_map = (Map) it2.next();
                    String version= version_map.get("version_type");
                    ver.add(version);
                }
                logger.info("ver："+ver);
                JSONObject versionlist = new JSONObject();
                versionlist.put("String",ver);


                film.put("id",showid);
                film.put("remark",remark);
                film.put("show_name",show_name);
                film.put("show_name_en",show_name_en);
                film.put("director",director);
                film.put("leading_role",leading_role);
                film.put("type",filmtype);
                film.put("country",country);
                film.put("language",language);
                film.put("duration",duration);
                film.put("open_day",open_day);
                film.put("poster",poster);
                film.put("description",description);
                film.put("poster",poster);
                film.put("show_version_list",versionlist);
                film.put("open_time",open_time);
                film.put("highlight","Wery Good");
                film.put("background_picture",background_picture);
                film.put("trailer_list",trailer_list);
                ;
                hot_filmObjects.add(film);

            }
            JSONObject mtop_cinema2 = new JSONObject();
            mtop_cinema2.put("show",hot_filmObjects);


            JSONObject hot_shows = new JSONObject();
            hot_shows.put("soon_shows",mtop_cinema2);

            JSONObject return_value = new JSONObject();
            return_value.put("return_value",hot_shows);
            return_value.put("return_code","0");
            return_value.put("return_message","Success");

            JSONObject result = new JSONObject();
            result.put("result",return_value);


            JSONObject response = new JSONObject();
            response.put("film_data_third_party_hotshows_get_response",result);


            out.println(response);


        }else {
            JSONObject return_value = new JSONObject();

            return_value.put("return_code","1");
            return_value.put("return_message","FAIL");

            JSONObject result = new JSONObject();
            result.put("result",return_value);


            JSONObject response = new JSONObject();
            response.put("film_data_third_party_hotshows_get_response",result);


            out.println(response);
        }

    }
}
