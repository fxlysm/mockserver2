package cn.imovie.mockserver.action;

import cn.imovie.mockserver.Wechat.util.StringUtil;
import cn.imovie.mockserver.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TPPCreatSchedules {

    private static final Logger logger = LoggerFactory.getLogger(TPPCreatSchedules.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Scheduled(cron = "${jobs.schedule.cron}")//每天早上两点自动生成排期  0 0 2 ? * *   //*/20 * * * * ?
    public  void CreatSchedules()   {


        String cinemas_command="SELECT * FROM tpp_cinemaslist c,tpp_cinema_hall h WHERE c.cinema_id = h.cinema_id";
        String film_comand="SELECT * FROM tpp_film WHERE schedules_state='1'";

        logger.info("查询影院 Command:"+cinemas_command);
        logger.info("查询影片 Command:"+film_comand);



        List filmrows = jdbcTemplate.queryForList(film_comand);
        logger.info("影片数："+filmrows.size());
        Iterator filmit = filmrows.iterator();

        while(filmit.hasNext()) {

            Map film_map = (Map) filmit.next();
            logger.info("影片MAP"+film_map);
            String show_id=film_map.get("show_id").toString();
            String show_version=film_map.get("show_version_list").toString();


            List rows = jdbcTemplate.queryForList(cinemas_command);
            logger.info("影厅数："+rows.size());
            Iterator rowsit = rows.iterator();
            while(rowsit.hasNext()) {
                Map cinemas_map = (Map) rowsit.next();
                logger.info("影厅MAP"+cinemas_map);
                String hall_name=cinemas_map.get("hall_name").toString();
                String hall_id=cinemas_map.get("hall_id").toString();
                String price=cinemas_map.get("price").toString();
                String service_fee=cinemas_map.get("service_fee").toString();
                String cinema_id= cinemas_map.get("cinema_id").toString();
                String schedules_id= StringUtil.getStringDate("yyMMddHHmmss")+StringUtil.getCode(6,0);


                Map<String,String> showschedu= DateUtil.GetRandomDate();
                String show_time=showschedu.get("starttime");
                String close_time=showschedu.get("endtime");
                String show_date=showschedu.get("show_date");
                String section_id="00001";
                String schedule_area="123";

                String max_can_buy="4";

                String inset="'"+schedules_id+"','"+cinema_id+"','"+hall_id+"','"+show_id+"','"+show_date+"','"+show_time+"','"+show_version+"','"+close_time+"','"+hall_name+"','"+price+"','"+service_fee+"','"+section_id+"','"+schedule_area+"','"+max_can_buy+"'";

                String creat_command="INSERT INTO tpp_cinema_schedules(schedules_id,cinema_id,hall_id,show_id,show_date,show_time,show_version,close_time,hall_name,price,service_fee,section_id,schedule_area,max_can_buy) value ("+inset+")";
                logger.info("插入排期："+creat_command);
                jdbcTemplate.execute(creat_command);


            }



        }

//        //创建几条数据
//        for (int m=0;m<=10;m++){
//
//        }

    }

    public static  void main(String args[]) {

    }
}
