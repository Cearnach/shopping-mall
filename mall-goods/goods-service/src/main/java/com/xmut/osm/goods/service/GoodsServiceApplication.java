package com.xmut.osm.goods.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 阮胜
 * @date 2018/7/20 21:49
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GoodsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsServiceApplication.class, args);
    }
}
