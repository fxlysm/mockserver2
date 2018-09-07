package cn.imovie.mockserver.taopiaopiao.server;


import cn.imovie.mockserver.exception.GlobalExceptionHandler;
import cn.imovie.mockserver.result.Result;
import cn.imovie.mockserver.result.ResultUtil;
import cn.imovie.mockserver.taopiaopiao.controller.*;

import cn.imovie.mockserver.taopiaopiao.dao.TppDao;
import cn.imovie.mockserver.taopiaopiao.impl.GetCinemaImpl;
import cn.imovie.mockserver.util.MapJson;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@Api("淘票票票务相关api")
@RequestMapping(value = "/router")
public class TPPServer {
    private static final Logger logger = LoggerFactory.getLogger(TPPServer.class);

    @Autowired
    private Getsoonshows soonshows;

    @Autowired
    private Gethotshows gethotshows;

    @Autowired
    private Getshowcomments getshowcomments;

    @Autowired
    private Getregions getregions;

    @Autowired
    private GetCinemaSeatMap getCinemaSeatMap;


    @Autowired
    private GetCinemaImpl getCinema;

    @Autowired
    private GetCinemaSchedules getCinemaSchedules;



    @Autowired
    private LockSeat lockSeat;

    @Autowired
    private UnLockSeat unLockSeat;

    @Autowired
    private Order order;

    @ApiOperation("票务接口")
//    @ResponseBody
    @RequestMapping(value = "/rest", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public void  getPay(HttpServletResponse resp, @RequestBody JSONObject jsonParam, PrintWriter out)throws Exception{//,@RequestParam Map<String, String> map,@Valid TppDao tpp,  BindingResult bindingResult,
//        String message="";
//        String jsonstr=tpp.toString();
        logger.info("接收到参数Json：:"+jsonParam);
//        System.out.println("接收到参数Json：:"+jsonstr);
//        logger.info("接收到参数MAP：:"+map);
//        if(bindingResult.hasErrors()){
//            for (ObjectError error : bindingResult.getAllErrors()) {
//                message=error.getDefaultMessage();
////                out.print(ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage()));
//            }
//            out.print(ResultUtil.ErrorHandler("1",message));
//        }else {


            Map<String,String> map = MapJson.Json2Map(jsonParam.toString());

            if("taobao.film.data.third.party.regions.get".equals(map.get("method"))){
                getregions.Getregions(resp,map);

            }else if("taobao.film.data.third.party.soonshows.get".equals(map.get("method"))){
                soonshows.Getsoonshows(map,out);
            }else if("taobao.film.data.third.party.hotshows.get".equals(map.get("method"))){
                gethotshows.Gethotshows(map,out);
            }else if("taobao.film.data.third.party.showcomments.get".equals(map.get("method"))){
                getshowcomments.Getshowcomments(map,out);
            }else if("taobao.film.data.third.party.cinemas.get".equals(map.get("method"))){
                getCinema.Getcinemas(map,out);
            }else if("taobao.film.data.third.party.schedules.get".equals(map.get("method"))){
                getCinemaSchedules.GetCinemaSchedules(map,out);
            }else if("taobao.film.data.third.party.seat.map".equals(map.get("method"))){
                getCinemaSeatMap.GetCinemaSeatMap(map,out);
            }else if("taobao.film.data.third.party.lock.seat".equals(map.get("method"))){
                lockSeat.LockSeat(map,out);

            }else if("taobao.film.data.third.party.unlock.seat".equals(map.get("method"))){
                unLockSeat.UnLockSeat(map,out);

            }else if("taobao.film.data.third.party.issue.order".equals(map.get("method"))){
                order.Order(map,out);
            }else if("taobao.tmc.user.permit".equals(map.get("method"))){

            }else
            {
                out.print(ResultUtil.ErrorHandler("1","method not support"));
            }






//        }


    }
//
//    @ResponseBody
//    @RequestMapping(value = "/refund", method = RequestMethod.POST)
//    public void getrefund(@RequestParam Map<String, String> map , PrintWriter out){
//        logger.debug("MAP:"+map);
//        logger.debug("************接收XX*********************");
//        String service=map.get("service");
//
//
//
//    }


}
