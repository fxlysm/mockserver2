package cn.imovie.mockserver.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能描述：完成与日期相关的各种操作
 * <p>
 * <p>
 * 包括将日期格式化、从字符串中解析出对应的日期、对日期的加减操作等
 *
 * @author maluming 2011-4-14
 * @see
 * @since 1.0
 */
@Slf4j
public class DateUtil {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    public static final String sdf_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static final String DATE_FORM = "yyyy-MM-dd";

    public static SimpleDateFormat sdf0 = new SimpleDateFormat("YYYYMMddHHmmss");

    /**
     * 功能描述：按照给出格式解析出日期
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     * @throws ParseException
     */
    public static Date parseDate(String dateStr, String format) throws ParseException {
        Date date = null;
        if (StringUtils.isNotBlank(dateStr)) {
            date = new SimpleDateFormat(format).parse(dateStr);
        }
        return date;
    }

    /**
     * 功能描述：格式化日期
     *
     * @param dateStr String 字符型日期：YYYY-MM-DD 格式
     * @return Date
     * @throws ParseException
     */
    public static Date parseDate(String dateStr) {
        try {
            return parseDate(dateStr, "yyyy-MM-dd");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDateTime(String dateStr) {
        try {
            return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能描述：格式化输出日期
     *
     * @param date   Date 日期
     * @param format String 格式
     * @return 字符型日期
     */
    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                result = new SimpleDateFormat(format).format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 功能描述：返回字符型时间
     *
     * @param date Date 日期
     * @return 返回字符型时间 HH:mm:ss 格式
     */
    public static String getTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    /**
     * 功能描述：取得指定月份的第一天
     *
     * @param strdate String 字符型日期
     * @return String yyyy-MM-dd 格式
     * @throws ParseException
     */
    public static String getMonthBegin(String strdate) throws ParseException {
        Date date = parseDate(strdate);
        return format(date, "yyyy-MM") + "-01";
    }

    /**
     * 功能描述：常用的格式化日期
     *
     * @param date Date 日期
     * @return String 日期字符串 yyyy-MM-dd格式
     */
    public static String formatDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String formatDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 计算2个日期之间的相隔天数
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return 日期1和日期2相隔天数
     */
    public int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) {
            // swap dates so that d1 is start and d2 is end
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 计算2个日期之间的工作天数（去除周六周日）
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return 日期1和日期2之间的工作天数
     */
    public int getWorkingDay(Calendar d1, Calendar d2) {
        int result = -1;
        if (d1.after(d2)) {
            // swap dates so that d1 is start and d2 is end
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }

        // int betweendays = getDaysBetween(d1, d2);

        // int charge_date = 0;

        // 开始日期的日期偏移量
        int charge_start_date = 0;
        // 结束日期的日期偏移量
        int charge_end_date = 0;

        int stmp;
        int etmp;
        stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);

        // 日期不在同一个日期内
        if (stmp != 0 && stmp != 6) {// 开始日期为星期六和星期日时偏移量为0
            charge_start_date = stmp - 1;
        }
        if (etmp != 0 && etmp != 6) {// 结束日期为星期六和星期日时偏移量为0
            charge_end_date = etmp - 1;
        }
        // }
        result = (getDaysBetween(this.getNextMonday(d1), this.getNextMonday(d2)) / 7) * 5 + charge_start_date
                - charge_end_date;
        // System.out.println("charge_start_date>" + charge_start_date);
        // System.out.println("charge_end_date>" + charge_end_date);
        // System.out.println("between day is-->" + betweendays);
        return result;
    }

    /**
     * 获取当前星期
     *
     * @param date      当前日期
     * @param character zh : 标识中文 ， en : 标识英文（默认）
     * @return 当前日期
     */
    public String getChineseWeek(Calendar date, String character) {
        String dayNames[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        if ("zh".equals(character)) {
            dayNames[0] = "星期日";
            dayNames[1] = "星期一";
            dayNames[2] = "星期二";
            dayNames[3] = "星期三";
            dayNames[4] = "星期四";
            dayNames[5] = "星期五";
            dayNames[6] = "星期六";
        }
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);

        // System.out.println(dayNames[dayOfWeek - 1]);
        return dayNames[dayOfWeek - 1];

    }

    /**
     * 获取前一天的时间
     *
     * @param date
     * @return
     */
    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获得日期的下一个星期一的日期
     *
     * @param date
     * @return
     */
    public Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }

    /**
     * 计算两个日期之间的非工作日天数
     *
     * @param d1 日期1
     * @param d2 日期2
     * @return 日期1与日期2之间的非工作天数
     */
    public int getHolidays(Calendar d1, Calendar d2) {
        return this.getDaysBetween(d1, d2) - this.getWorkingDay(d1, d2);

    }

    public static String formatDatayyMMDDHHMMSS(Date date) {
        return sdf0.format(date);
    }

    public static Date getTruncDate() {
        return DateUtil.getTruncDate(new Date());
    }

    public static Date getTruncDate(Date date) {
        return DateUtil.parseDate(DateUtil.formatDate(date));
    }

    public static Date getNextDate(Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime() + 24 * 60 * 60 * 1000);
    }

    public static Date getPreDate(Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime() - 24 * 60 * 60 * 1000);
    }

    /**
     * 计算两个日期相差的天数
     *
     * @param endDate
     * @param startDate
     * @return
     */
    public static long getDaysOfDifference(Date endDate, Date startDate) {
        return (DateUtil.getTruncDate(endDate).getTime() - DateUtil.getTruncDate(startDate).getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取相加后的日期（yyyy-MM-dd）
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getAfterAdd(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        Date resultDate = calendar.getTime();
        return parseDate(formatDate(resultDate));
    }

    public static Boolean contrastYear(String year) {
        Calendar ca = Calendar.getInstance();
        int currYear = ca.get(Calendar.YEAR);
//        log.info("current" + currYear);
        int argYear = Integer.parseInt(year);
        if (argYear <= currYear) {//返回true说明：传入的年份在当前年份之前
            return true;
        } else {//返回false说明：传入的年份在当前年份之后
            return false;
        }
    }


  public static String creatdate(){
      Random rand   =   new Random();
      SimpleDateFormat   format   =   new SimpleDateFormat( "yyyy-MM-dd ");
      Calendar   cal   =   Calendar.getInstance();
      cal.set(2018,   9,   20);
      long   start   =   cal.getTimeInMillis();
      cal.set(2018,   11,   1);
      long   end   =   cal.getTimeInMillis();

      Date   d   =   new Date(start   +   (long)(rand.nextDouble()   *   (end   -   start)));
      System.out.println(format.format(d));

      return  format.format(d);
  }


    private static String randomDate(){
        Random rndYear=new Random();
        int year=rndYear.nextInt(18)+2000;
        Random rndMonth=new Random();
        int month=rndMonth.nextInt(12)+1;
        Random rndDay=new Random();
        int Day=rndDay.nextInt(30)+1;
        Random rndHour=new Random();
        int hour=rndHour.nextInt(23);
        Random rndMinute=new Random();
        int minute=rndMinute.nextInt(60);
        Random rndSecond=new Random();
        int second=rndSecond.nextInt(60);
        return year+"-"+cp(month)+"-"+cp(Day)+" "+cp(hour)+":"+cp(minute)+":"+cp(second);
    }
    private static String cp(int num){
        String Num=num+"";
        if (Num.length()==1){
            return "0"+Num;
        }else {
            return Num;
        }
    }


    public static Map GetRandomDate(){

        Map<String,String> map =new HashMap<String, String>();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        Random rnd=new Random();
        String minutes[]={"00","05","10","15","20","30","40","50"};
        int mm=	(int)(Math.random()*(8));

        int date_r=(date+1)+rnd.nextInt(5);
        int hour_r=hour+rnd.nextInt(12);

        if(month==12&&date_r>=30){

            String starttime=(year+1)+"-01"+"-"+cp(rnd.nextInt(5))+" "+cp(hour)+":"+minutes[mm]+":00";
            String endtime=(year+1)+"-01"+"-"+cp(rnd.nextInt(5))+" "+cp(hour+2)+":"+minutes[mm]+":00";
            String show_date=(year+1)+"-01"+"-"+cp(rnd.nextInt(5));
            map.put("starttime",starttime);
            map.put("endtime",endtime);
            map.put("show_date",show_date);
            return map;
        }else {

            if (hour_r>=24){
                String starttime= year+"-"+cp(month+1)+"-"+cp(date_r)+" "+cp(hour_r-24)+":"+minutes[mm]+":00";
                String endtime= year+"-"+cp(month+1)+"-"+cp(date_r)+" "+cp(hour_r-24+2)+":"+minutes[mm]+":00";
                String show_date=year+"-"+cp(month+1)+"-"+cp(date_r);
                map.put("starttime",starttime);
                map.put("endtime",endtime);
                map.put("show_date",show_date);
                return map;
            }else
            {
                String starttime=  year+"-"+cp(month+1)+"-"+cp(date_r)+" "+cp(hour_r)+":"+minutes[mm]+":00";
                String endtime=  year+"-"+cp(month+1)+"-"+cp(date_r)+" "+cp(hour_r+2)+":"+minutes[mm]+":00";
                String show_date=year+"-"+cp(month+1)+"-"+cp(date_r);
                map.put("starttime",starttime);
                map.put("endtime",endtime);
                map.put("show_date",show_date);
                return map;
            }


        }

    }

    public static  void main(String args[]) {

        for(int i=0;i<100;i++){
            System.out.println(GetRandomDate());
        }

    }
}
