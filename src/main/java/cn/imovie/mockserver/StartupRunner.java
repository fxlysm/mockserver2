package cn.imovie.mockserver;

import cn.imovie.mockserver.Wechat.controller.WechatPayServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

//@Component
//@Order(value=1)//使用@Order 注解来定义执行顺序
public class StartupRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);
    @Override
    public void run(String... args) {
//        System.out.println();
        logger.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<"+ LocalDate.now());

    }
}
