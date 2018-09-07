package cn.imovie.mockserver.exception;


import cn.imovie.mockserver.conf.CodeConstant;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@ControllerAdvice
public class GlobalExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public CommonResp handleException(HttpServletRequest request, Exception ex) throws Exception {
//        String errorNo = DateUtil.format(new java.util.Date(), "yyyyMMddHHmmss")+(new Random()).nextInt(10000);
//        logger.error("请求出错："+errorNo, ex);
//        throw ex;
//    }
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.addObject("dever", "@liuyong");
//        mav.setViewName("error");
//        return mav;
//    }


    @ExceptionHandler(value = MyException.class)
    public ModelAndView myErrorHandler(MyException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code", ex.getCode());
        modelAndView.addObject("msg", ex.getMsg());
        modelAndView.addObject("dever", "@liuyong");
        return modelAndView;
    }
//
//
//
//
//    @ExceptionHandler(value = MyException.class)
//    @ResponseBody
//    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
//        ErrorInfo<String> r = new ErrorInfo<>();
//        r.setMessage(e.getMessage());
//        r.setCode(ErrorInfo.ERROR);
//        r.setUrl(req.getRequestURL().toString());
//        return r;
//    }
//@ExceptionHandler(value = BindException.class)
//public ErrorInfo<String> paramErrorHandler(HttpServletRequest req, HttpServletResponse resp, BindException e) {
//    String code = CodeConstant.ERROR_CODE_PARAMS_ERROR;
//    logger.info("请求参数校验失败：{code: " + code + "," + "errorMsg：" + CodeConstant.MAP_CODE_DESC.get(code) + "}");
//    // 设置响应的Http状态
////        resp.setStatus(Integer.parseInt(code.substring(0, 3)));
//
//    FieldError error = e.getFieldError();
//    String fieldName = error.getField();
//    String message = error.getDefaultMessage();
//    logger.info("{} : {}", fieldName, message);
//
//    ErrorInfo<String> r = new ErrorInfo<>();
//    r.setErrorCode(code);
//    r.setErrorMsg("参数"+fieldName + "输入格式有误");
//
//    return r;
//}
//
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ErrorInfo<String> paramErrorHandler(HttpServletRequest req, HttpServletResponse resp, MethodArgumentNotValidException e) {
//        String code = CodeConstant.ERROR_CODE_PARAMS_ERROR;
//        logger.info("请求参数校验失败：{code: " + code + "," + "errorMsg：" + CodeConstant.MAP_CODE_DESC.get(code) + "}");
//
//        FieldError error = e.getBindingResult().getFieldError();
//        String fieldName = error.getField();
//        String message = error.getDefaultMessage();
//        logger.info("{} : {}", fieldName, message);
//
//        ErrorInfo<String> r = new ErrorInfo<>();
//        r.setErrorCode(code);
//        r.setErrorMsg("参数"+fieldName + "输入格式有误");
//
//        return r;
//    }
//
//
//
//
//    /**
//     * 前面处理的异常都到这里
//     * 统一标记为系统异常
//     * @param req
//     * @param resp
//     * @param e
//     * @return
//     * @throws Exception
//     */
//    @ExceptionHandler(value = Exception.class)
//    public ErrorInfo<String> errorHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) throws Exception {
//
//        if (e.getCause() != null) {
//            e.getCause().printStackTrace(); // 打印引起此错误的日志
//            logger.error("系统异常", e);
//        } else {
//            e.printStackTrace();
//            logger.error("系统异常", e);
//        }
//
//        String code = CodeConstant.ERROR_CODE_SYSTEM_ERROR;
//        logger.info("系统发生异常：{code: " + code + "," + "errorMsg：" + CodeConstant.MAP_CODE_DESC.get(code) + "}");
//        // 设置响应的Http状态
////        resp.setStatus(Integer.parseInt(code.substring(0, 3)));
//
//        ErrorInfo<String> r = new ErrorInfo<>();
//        r.setErrorCode(code);
//        String message = CodeConstant.MAP_CODE_DESC.get(CodeConstant.ERROR_CODE_SYSTEM_ERROR);
//        r.setErrorMsg(message);
//
//        return r;
//    }

//    /**
//     * serviceName参数不传或者传入有误抛出的异常
//     * @param req
//     * @param resp
//     * @param e
//     * @return
//     * @throws Exception
//     */
//    @ExceptionHandler(value = UnsatisfiedServletRequestParameterException.class)
//    public ErrorInfo<String> requestParameterExceptionHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) throws Exception {
//        String code;
//        String message;
//        if (StringUtils.isNotBlank(req.getParameter("serviceName"))) {
//            code = CodeConstant.ERROR_CODE_INVALID_SERVICE;
//            message = CodeConstant.MAP_CODE_DESC.get(code);
//        } else {
//            code = CodeConstant.ERROR_CODE_PARAMS_ERROR;
//            message = "参数serviceName输入格式有误";
//        }
//        logger.info("无效的serviceName参数" + req.getParameter("serviceName") + "：{code: " + code + "," + "errorMsg：" + CodeConstant.MAP_CODE_DESC.get(code) + "}");
//
//        ErrorInfo<String> r = new ErrorInfo<>();
//        r.setErrorCode(code);
//        r.setErrorMsg(message);
//
//        return r;
//    }
}
