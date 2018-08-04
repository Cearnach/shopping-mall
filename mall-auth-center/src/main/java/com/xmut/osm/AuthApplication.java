package com.xmut.osm;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 阮胜
 * @date 2018/8/4 15:54
 */
@ComponentScan(basePackages = "com.xmut.osm")
@SpringCloudApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
