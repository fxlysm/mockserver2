package cn.imovie.mockserver.Wechat.util;

import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(httpUtil.class);
    /**
     * http请求
     *
     * @param urlvalue
     *            指定URL路径地址
     * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
     *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
    public static String httpPostRequestXML(String urlvalue, String postStr) {

        String inputLine = "";

        logger.debug("http request: url:" + urlvalue + "\npost str:" + postStr);

        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
            // application/json;charset=ut8-8
            // text/xml; charset=UTF-8

            PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());

            printWriter.write(postStr);
            printWriter.flush();

            // 开始获取数据
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((in.read()) != -1) {// !=null
                inputLine = in.readLine().toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            inputLine = "";
        }

        return inputLine;
    }


}
