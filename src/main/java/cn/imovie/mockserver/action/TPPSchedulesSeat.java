package cn.imovie.mockserver.action;


import cn.imovie.mockserver.Wechat.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class TPPSchedulesSeat {
    private static final Logger logger = LoggerFactory.getLogger(TPPSchedulesSeat.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(cron = "${jobs.seat.cron}")//
    public void SnyCreat(){

        String schedulescomand="SELECT * FROM tpp_cinema_schedules s,tpp_cinema_hall_seat h WHERE s.hall_id=h.hall_id  AND s.iscreaded='0'";


        List schedulesrows = jdbcTemplate.queryForList(schedulescomand);
        logger.info("生成座位数："+schedulesrows.size());
        Iterator scheduleslist = schedulesrows.iterator();
        if(schedulesrows.size()>0) {
            while (scheduleslist.hasNext()) {
                Map schedules_map = (Map) scheduleslist.next();
                logger.info("MAP：" + schedules_map);

                String schedules_id = schedules_map.get("schedules_id").toString();
                String hall_id = schedules_map.get("hall_id").toString();

//            String seatscomand="SELECT * FROM tpp_cinema_hall_seat WHERE hall_id='"+hall_id+"'";
//            List seatsrows = jdbcTemplate.queryForList(seatscomand);
//            Iterator seats_list = seatsrows.iterator();
//
//            while(seats_list.hasNext()){
//                Map seats_map = (Map) seats_list.next();
//                logger.info("厅的座位图MAP："+seats_map);
//            }

                String cinema_id = schedules_map.get("cinema_id").toString();
                String show_id = schedules_map.get("show_id").toString();
                String show_date = schedules_map.get("show_date").toString();
                String show_time = schedules_map.get("show_time").toString();
                String show_version = schedules_map.get("show_version").toString();
                String close_time = schedules_map.get("close_time").toString();
                String hall_name = schedules_map.get("hall_name").toString();
                String price = schedules_map.get("price").toString();
                String service_fee = schedules_map.get("service_fee").toString();
                String section_id = schedules_map.get("section_id").toString();
                String ext_id = schedules_map.get("ext_id").toString();
                String schedule_area = schedules_map.get("schedule_area").toString();
                String seat_row = schedules_map.get("seat_row").toString();
                String seat_column = schedules_map.get("seat_column").toString();
                String row_name = schedules_map.get("row_name").toString();
                String seat_name = schedules_map.get("seat_name").toString();
                String area = schedules_map.get("area").toString();
                String left_px = schedules_map.get("left_px").toString();
                String top_px = schedules_map.get("top_px").toString();
                String default_status = schedules_map.get("default_status").toString();
                String flag = schedules_map.get("flag").toString();


                String seat_id = StringUtil.getStringDate("yyMMddHHmmss") + StringUtil.getCode(8, 0);

                String inset = "'" + seat_id + "','" + schedules_id + "','" + cinema_id + "','" + hall_id + "','" + show_id + "','" + top_px + "','" + left_px + "','" + seat_column + "','" + seat_row + "','" + row_name + "','" + seat_name + "','" + ext_id + "','" + area + "','" + default_status + "','" + flag + "'";

                List filmtype_rows = jdbcTemplate.queryForList("SELECT * FROM tpp_cinema_schedule_seats WHERE schedules_id='" + schedules_id + "'AND hall_id='" + hall_id + "'AND show_id='" + show_id + "'AND ext_id='" + ext_id + "'");
                if (filmtype_rows.size() > 0) {
                    logger.info("提示：已存在该排期座位，放弃生成排期座位");

                } else {
                    String creat_command = "INSERT INTO tpp_cinema_schedule_seats(seat_id,schedules_id,cinema_id,hall_id,show_id,top_px,left_px,seat_column,seat_row,row_name,seat_name,ext_id,area,seat_status,flag) VALUE (" + inset + ")";
                    jdbcTemplate.execute(creat_command);


                }
                //更新排期
                String schedule_command = "UPDATE tpp_cinema_schedules SET iscreaded='1' WHERE schedules_id='" + schedules_id + "'";
                jdbcTemplate.execute(schedule_command);

            }

        }else {
            logger.info("提示：未有生成新的排期，不生成座位数据");
        }
    }

}
