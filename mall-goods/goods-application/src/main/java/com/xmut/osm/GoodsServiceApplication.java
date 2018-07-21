package com.xmut.osm;

import com.xmut.osm.repository.base.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author 阮胜
 * @date 2018/7/20 21:49
 */
@EnableFeignClients(basePackages = "com.xmut.osm")
@ComponentScan(basePackages = "com.xmut.osm")
@SpringCloudApplication
@EnableJpaRepositories(basePackages = "com.xmut.osm", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class GoodsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsServiceApplication.class, args);
    }
}
