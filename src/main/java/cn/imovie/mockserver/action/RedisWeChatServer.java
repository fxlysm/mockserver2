package cn.imovie.mockserver.action;


import cn.imovie.mockserver.Wechat.server.payNoticeServer;
import cn.imovie.mockserver.conf.SystemConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

@Component
public class RedisWeChatServer {

    private static final Logger logger = LoggerFactory.getLogger(RedisWeChatServer.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    String PFMOCK= SystemConf.WECHATMOCK_MSG;

    @Autowired
    private payNoticeServer payNotice;

    @Scheduled(cron = "*/10 * * * * ?")
    public  void payNotify()   {
        int count= redisTemplate.opsForHash().keys(PFMOCK).size();
        logger.info("**********读取微信订单缓存个数："+count+"**********************");


        Set<Object> set  =redisTemplate.opsForHash().keys(PFMOCK);
        Iterator<Object> it = set.iterator();
        while (it.hasNext()){
            Object ob =  it.next();
            logger.info("正在处理订单："+ob);
//            Object v = redisTemplate.opsForValue().get(ob);
//            System.out.println("key:" +ob +", value:" +v);
            Object v=   redisTemplate.opsForHash().get(PFMOCK,ob);
//            System.out.println("key:" +ob +", value:" +v);
//            redisTemplate.opsForHash().delete(PFMOCK,ob);

            JSONObject jsonObject = JSONObject.parseObject((String) v);
            Map<String, String> map = JSONObject.parseObject(jsonObject.toJSONString(),
                    new TypeReference<Map<String, String>>() {
                    });
            System.out.println(map);

            try {
                if(map.get("return_code").equals("SUCCESS")){
                    payNotice.payNotify(map);

                    logger.info("订单处理完成"+ob);
                }else {
                    logger.info("订单支付return_code为非SUCCESS，不做通知处理");
                }

                redisTemplate.opsForHash().delete(PFMOCK,ob);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    }
}
