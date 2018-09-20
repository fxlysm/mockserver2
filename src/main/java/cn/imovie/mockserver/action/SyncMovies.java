package cn.imovie.mockserver.action;

import cn.imovie.mockserver.taopiaopiao.util.filmCodeUtil;
import cn.imovie.mockserver.util.DateUtil;
import cn.imovie.mockserver.util.HttpClientUtil;

import cn.imovie.mockserver.util.JsonUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SyncMovies {

    private static final Logger logger = LoggerFactory.getLogger(SyncMovies.class);

    private  String charset = "utf-8";
    private  HttpClientUtil httpClientUtil = null;

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    @Value("${zgdypw.film.url}")
    private String url;


    @Scheduled(cron = "${jobs.film.cron}")
    public  void sync() {
        httpClientUtil = new HttpClientUtil();
        String httpOrgCreateTest = url ;
        Map<String,String> createMap = new HashMap<String,String>();
        createMap.put("s_showYear","2018");
        createMap.put("size","100");
        String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);
        System.out.println("result:"+httpOrgCreateTestRtn);


        JSONObject json = JSONObject.parseObject(httpOrgCreateTestRtn);

        Set<String> keys = json.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = String.valueOf(json.get(key));
            if ("data".equals(key)) {
                JSONArray recordArray = json.getJSONArray("data");
                JSONArray jsonArray = new JSONArray(recordArray);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    String code = jsonObj.getString("code");//
                    String publishVersionName = jsonObj.getString("publishVersionName");
                    String publishVersion = jsonObj.getString("publishVersion");//
                    String publisherName = jsonObj.getString("publisherName");
                    String producerName = jsonObj.getString("producerName");
                    String filmname = jsonObj.getString("name");//
                    String typeName = jsonObj.getString("typeName");
                    String publishDate = DateUtil.stampToTime(jsonObj.getString("publishDate"));
                    String opendaty= DateUtil.stampToDate(jsonObj.getString("publishDate"));
                    String id = jsonObj.getString("id");
                    String filmtype = jsonObj.getString("type");
                    System.out.println("code:" + code + " name" + filmname+" publishDate:"+publishDate);

                    String countriescode= filmCodeUtil.Getcountries(code.substring(0,3));//code.substring(0,3)
                    String filmtypestr=filmCodeUtil.GetfilmType(code.substring(3,4));
                    String filversion=filmCodeUtil.Getfilmversion(code.substring(3,4));
                    logger.info("countriescode:"+countriescode);
                    logger.info("filmtypestr:"+filmtypestr);

                   String filmId=code.substring(0,3)+"X"+code.substring(4);
                   String filmversion=filmCodeUtil.GetfilmType(code.substring(3,4));

                    List rows =  jdbcTemplate.queryForList("SELECT * FROM tpp_film WHERE show_id='"+filmId+"'");
                    if(rows.size()>0){

                        logger.info(jsonObj.toString()+"---存在记录");
                    }else {

                        logger.info(jsonObj.toString()+"---不存在记录");

                        String command="INSERT INTO tpp_film(show_id,show_name,show_version_list,filmtype,open_day,open_time,country)value ('"+filmId+"','"+filmname+"','"+publishVersionName+"','"+typeName+"','"+publishDate+"','"+opendaty+"','"+countriescode+"')";
                        logger.info("正在插入影片记录");
                        logger.info("MySql:"+command);
                        jdbcTemplate.execute(command);

                    }

                    List filmtype_rows =  jdbcTemplate.queryForList("SELECT * FROM tpp_film_version WHERE film_id='"+filmId+"'AND film_type_name='"+filmtypestr+"'");
                    if(filmtype_rows.size()>0){
                        logger.info("已存在影片版本记录");
                    }else {
                        String command="INSERT INTO tpp_film_version(film_id,version_type,film_type_name)value ('"+filmId+"','"+filversion+"','"+filmtypestr+"')";
                        logger.info("正在插入影片版本记录");
                        logger.info("MySql:"+command);
                        jdbcTemplate.execute(command);
                    }









                }

            }
        }

    }





    public static   void main(String[] args){
      String code="123456789";
      System.out.println(code.substring(3,4));
    }
}
