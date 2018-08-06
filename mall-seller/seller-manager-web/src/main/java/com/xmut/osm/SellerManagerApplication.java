package com.xmut.osm;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 阮胜
 * @date 2018/8/6 16:52
 */
@EnableFeignClients(basePackages = "com.xmut.osm")
@ComponentScan(basePackages = "com.xmut.osm")
@SpringCloudApplication
public class SellerManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SellerManagerApplication.class);
    }
}
