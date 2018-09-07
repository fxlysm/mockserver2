package cn.imovie.mockserver.result;

import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static String ErrorHandler(String code, String str) {
        JSONObject jsonObjArr = new JSONObject();
        jsonObjArr.put("msg", str);
        jsonObjArr.put("code", code);

        JSONObject msg = new JSONObject();
        msg.put("error_response", jsonObjArr);

        return msg.toString();
    }
}