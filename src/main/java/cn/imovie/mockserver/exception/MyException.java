package cn.imovie.mockserver.exception;

public class MyException extends Exception {

   private String code;
    private String msg;

    public MyException(String code,String message) {
        super(message);

    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
