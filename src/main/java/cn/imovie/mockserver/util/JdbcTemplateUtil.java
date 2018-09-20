package cn.imovie.mockserver.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateUtil {
    @Autowired
    private  static JdbcTemplate jdbcTemplate;

    /**
     * 查询返回某一个值：查询表中数据总数
     */
    public static int queryForOneInt(String sql) {
//        String sql = "select count(*) from user";
//        调用方法获得记录数
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
       return count;
    }

    /**
     * 查询返回某一个值：查询表中数据总数
     */
    public static String queryForOneString(String sql) {
//        String sql = "select count(*) from user";
//        调用方法获得记录数
        String count = jdbcTemplate.queryForObject(sql, String.class);
        return count;
    }

}
