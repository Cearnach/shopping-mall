package com.xmut.osm.goods.test.repository;

import com.xmut.osm.entity.SpecificationOption;
import com.xmut.osm.goods.test.BaseTest;
import com.xmut.osm.repository.SpecificationOptionRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/30 23:35
 */
public class SpecificationOptionTest extends BaseTest {
    @Autowired
    SpecificationOptionRepository specificationOptionRepository;

    @Test
    public void find() {
        List<SpecificationOption> list = specificationOptionRepository.findBySpecificationId(132);
        System.out.println(list);
    }
}
