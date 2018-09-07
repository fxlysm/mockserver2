package cn.imovie.mockserver.result;

import java.util.HashMap;
import java.util.Map;

/**
 * http请求返回的最外层对象
 * Created by 廖师兄
 * 2017-01-21 13:34
 */
public class Result<T> {
    public static final int OK = 0; //成功
    public static final int FAILED = -1; //失败

    public static final int NOT_LOGIN = -100; //未登录

    public static final int ERROR_SYSTEM = -101; //系统错误

    public static final int NOT_UNAUTHORIZED = -102; //未授权

    public static final int LOGIN_FAILED = -104; //登陆失败

    public static final int PARAM_NOT_RIGHT = -201; //查询条件参数不全,错误等

    //错误消息
    private static Map<Integer,String> msgs = new HashMap<Integer,String>();

    static {
        msgs.put(OK, "成功");
        msgs.put(FAILED, "失败");
        msgs.put(NOT_LOGIN, "未登录");
        msgs.put(ERROR_SYSTEM, "系统繁忙");//其实是系统未捕获异常，提示繁忙用户体验好一点
        msgs.put(NOT_UNAUTHORIZED, "未授权");
        msgs.put(LOGIN_FAILED, "登陆失败");
        msgs.put(PARAM_NOT_RIGHT, "条件参数不正确");
    }
    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体的内容. */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
