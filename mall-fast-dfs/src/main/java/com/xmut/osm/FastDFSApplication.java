package com.xmut.osm;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 阮胜
 * @date 2018/8/16 15:49
 */
@ComponentScan(basePackages = "com.xmut.osm")
@SpringCloudApplication
public class FastDFSApplication {
    public static void main(String[] args)  {
        SpringApplication.run(FastDFSApplication.class, args);
    }
}
