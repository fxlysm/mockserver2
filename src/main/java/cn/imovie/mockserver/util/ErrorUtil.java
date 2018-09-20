package cn.imovie.mockserver.util;

import org.json.JSONObject;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

public class ErrorUtil {

    public  static void prserror(String sub_msg,int code,String sub_code,String msg,PrintWriter out){

        JSONObject error=new JSONObject();
        error.put("sub_msg",sub_msg);
        error.put("code",code);
        error.put("sub_code",sub_code);
        error.put("msg",msg);
        JSONObject error_response=new JSONObject();
        error_response.put("error_response",error);

        out.println(error_response);
    }
}
