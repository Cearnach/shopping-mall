package com.xmut.osm;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 阮胜
 * @date 2018/7/22 23:42
 */
@EnableFeignClients(basePackages = "com.xmut.osm")
@ComponentScan(basePackages = "com.xmut.osm")
@SpringCloudApplication
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
