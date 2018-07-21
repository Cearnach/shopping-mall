package com.xmut.osm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 阮胜
 * @date 2018/7/20 21:27
 */
@SpringBootApplication
@EnableEurekaServer
public class CloudCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudCenterApplication.class, args);
    }
}
