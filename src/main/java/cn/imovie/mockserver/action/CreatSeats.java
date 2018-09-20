package cn.imovie.mockserver.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/***
 * 仅为自动创建影厅座位----创建完成后废弃
 */

//@Component
public class CreatSeats {
    private static final Logger logger = LoggerFactory.getLogger(CreatSeats.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(cron = "0 56 17 * * ?")
    public void Creat(){

        String hallcomand="SELECT * FROM tpp_cinema_hall WHERE hall_status='1'";


        List hallrows = jdbcTemplate.queryForList(hallcomand);
        logger.info("影片数："+hallrows.size());
        Iterator halllist = hallrows.iterator();

        while(halllist.hasNext()) {

            Map hallmap = (Map) halllist.next();
            logger.info("影厅"+hallmap);
            String max_row= hallmap.get("max_row").toString();
            String max_column= hallmap.get("max_column").toString();
            int row= Integer.parseInt(max_row);
            int column= Integer.parseInt(max_column);
            String hall_id=hallmap.get("hall_id").toString();
            String cinema_id=hallmap.get("cinema_id").toString();
            String hall_name=hallmap.get("hall_name").toString();
            String price=hallmap.get("price").toString();
            String service_fee=hallmap.get("service_fee").toString();
            String regular=hallmap.get("regular").toString();
            String max_top_px=hallmap.get("max_top_px").toString();
            String max_left_px=hallmap.get("max_left_px").toString();

            for (int i=1;i<=row;i++){
                String seat_row = String.valueOf(i);
                String row_name="第"+i+"排";
                for (int j=1;j<=column;j++){
                   String seat_column = String.valueOf(j);
                    String ext_id= hall_id+String.valueOf(i)+String.valueOf(j);
                    String seat_name="第"+i+"排"+j+"列";
                    String area=null;
                    String creat_command="INSERT INTO tpp_cinema_hall_seat (hall_id,ext_id,top_px,left_px,seat_column,seat_row,row_name,seat_name,area) VALUE ('"+hall_id+"','"+ext_id+"','"+max_top_px+"','"+max_left_px+"','"+seat_column+"','"+seat_row+"','"+row_name+"','"+seat_name+"','"+area+"')";
                    jdbcTemplate.execute(creat_command);

                }
            }


        }
    }
}
