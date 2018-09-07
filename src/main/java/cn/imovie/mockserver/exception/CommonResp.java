package cn.imovie.mockserver.exception;


import cn.imovie.mockserver.result.Result;

/**
 * 通用返回响应
 */
public class CommonResp<T> {

    private Integer result = Result.OK;

    private T value; //返回值

    private String msg = "成功";

    public CommonResp() {
    }

    public CommonResp(Integer code, String msg) {
        this.result = code;
        this.msg = msg;
    }

    public CommonResp(Integer code, String msg, T data) {
        this(code, msg);
        this.value = data;
    }

    public static CommonResp getSuccResult() {
        return new CommonResp(Result.OK, "success");
    }

    public static CommonResp getSuccResult(String msg) {
        return new CommonResp(Result.OK, msg);
    }

    public static CommonResp getSuccResult(Object data) {
        return new CommonResp<>(Result.OK, "success", data);
    }

    public static <T> CommonResp<T> getSuccResult(String msg, T data) {
        return new CommonResp<>(Result.OK, msg, data);
    }

    public static CommonResp getErrorResult() {
        return new CommonResp(Result.FAILED, "系统繁忙, 请稍后重试!");
    }

    public static CommonResp getErrorResult(String msg) {
        return new CommonResp(Result.FAILED, msg);
    }

    public static <T> CommonResp<T> getErrorResult(String msg, T data) {
        return new CommonResp<>(1, msg, data);
    }

    public static CommonResp getResult(Integer code, String msg) {
        return new CommonResp(code, msg);
    }

    public static <T> CommonResp<T> getResult(Integer code, String msg, T data) {
        return new CommonResp<>(code, msg, data);
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("CommonResp:{")
                .append("result:").append(result)
                .append("|msg:").append(msg)
                .append("}").toString();
    }
}
