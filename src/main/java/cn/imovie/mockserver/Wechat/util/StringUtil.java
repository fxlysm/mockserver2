package cn.imovie.mockserver.Wechat.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class StringUtil {
    /***
     * 字符串中英文小写转大写
     *
     * @param str
     * @return
     */
    public static String shift(String str) {
        int size = str.length();
        char[] chs = str.toCharArray();
        for (int i = 0; i < size; i++) {
            if (chs[i] <= 'Z' && chs[i] >= 'A') {
                chs[i] = (char) (chs[i] + 32);
            } else if (chs[i] <= 'z' && chs[i] >= 'a') {
                chs[i] = (char) (chs[i] - 32);
            }
        }
        return new String(chs);
    }


    /**
     * 获取特定格式时间
     *
     * @return
     */
    public static String getStringDate(String dateFormat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /***
     *
     * @param passLength
     *            字符串长度
     * @param type
     *            随机字符串类型 0. 为数字 1为小写字母 2为大写字母 3 大小写及数字随机字符
     * @return
     */
    public static String getCode(int passLength, int type) {
        StringBuffer buffer = null;
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        r.setSeed(new Date().getTime());
        switch (type) {
            case 0:
                buffer = new StringBuffer("0123456789");
                break;
            case 1:
                buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
                break;
            case 2:
                buffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                break;
            case 3:
                buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
                break;
            case 4:
                buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
                sb.append(buffer.charAt(r.nextInt(buffer.length() - 10)));
                passLength -= 1;
                break;
            case 5:
                String s = UUID.randomUUID().toString();
                sb.append(s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
                        + s.substring(24));
        }

        if (type != 5) {
            int range = buffer.length();
            for (int i = 0; i < passLength; ++i) {
                sb.append(buffer.charAt(r.nextInt(range)));
            }
        }
        return sb.toString();
    }
    public static String GetIp() {
        int max = 255;
        int min = 1;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        String ip = "";
        for (int i = 0; i < 3; i++) {
            ip += (random.nextInt(max) % (max - min + 1) + min) + ".";
        }
        ip += s;
        return ip;
    }

    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getSpecialcharacters(int length) { //length表示生成字符串的长度
        String base = "`~!@#$%^&*()_-=+;:'\"<>,.?/ []{}";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }



    public static String gettotal(String totalfee_min,String totalfee_max) {
        int max = Integer.valueOf(totalfee_max).intValue();
        int min = Integer.valueOf(totalfee_min).intValue();
        Random random = new Random();

        int s = random.nextInt(max) % (max - min + 1) + min;
        // System.out.println(s);
        String total_free=String.valueOf(s);
        return total_free;
    }

    public static String[] chars = new String[] {  "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9" };


    public static String generateShortUuid(int m) {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < m; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

//    /***
//     * 字符串中英文小写转大写
//     *
//     * @param str
//     * @return
//     */
//    public static String shift(String str) {
//        int size = str.length();
//        char[] chs = str.toCharArray();
//        for (int i = 0; i < size; i++) {
//            if (chs[i] <= 'Z' && chs[i] >= 'A') {
//                chs[i] = (char) (chs[i] + 32);
//            } else if (chs[i] <= 'z' && chs[i] >= 'a') {
//                chs[i] = (char) (chs[i] - 32);
//            }
//        }
//        return new String(chs);
//    }



    public static Map<String, String> getmap(String string){
        String[] names = string.split("&");

        Map<String, String> map = new HashMap<String, String>();
        for (int i=0;i<names.length;i++){
            String[] kk = names[i].split("=");
//            for (int j=0;j<kk.length;j++){
//
//            }
            System.out.println(kk[0]);
            System.out.println(kk[1]);
            map.put(kk[0],kk[1]);

        }

        return map;

    }
}
