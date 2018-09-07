package cn.imovie.mockserver.taopiaopiao.server;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class checkserver {
    @Retryable(value = {RemoteAccessException.class, RuntimeException.class},
            maxAttempts = 5,
            backoff = @Backoff(value = 20000))
    public void service() {
        System.out.println("这是PaycenterApi客户端接受\n接受地址：http://192.168.74.163:8070/pay/gateway");
        // this exception will just trigger recover1, do not trigger recover3
        throw new RemoteAccessException("remote access exception");
        // this exception will just trigger recover2
//        throw new RuntimeException("runtime exception");

//        System.out.println("do another things");

    }
}
