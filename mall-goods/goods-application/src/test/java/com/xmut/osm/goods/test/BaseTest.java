package com.xmut.osm.goods.test;

import com.xmut.osm.entity.Specification;
import com.xmut.osm.goods.service.SpecificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 阮胜
 * @date 2018/7/30 14:29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseTest {
    @Autowired
    private SpecificationService specificationService;

    @Test
    public void insertSpecification() {
        specificationService.save(new Specification("电视屏幕尺寸"));
        specificationService.save(new Specification("手机屏幕尺寸"));
        specificationService.save(new Specification("尺码"));
        specificationService.save(new Specification("网络"));
        specificationService.save(new Specification("机身内存"));
    }

}
