package cn.imovie.mockserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableRetry
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackages = "cn")
@SpringBootApplication
public class MockserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockserverApplication.class, args);
    }
}
