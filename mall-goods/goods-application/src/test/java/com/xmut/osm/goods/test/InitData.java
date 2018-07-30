package com.xmut.osm.goods.test;

import com.xmut.osm.entity.Specification;
import com.xmut.osm.entity.SpecificationOption;
import com.xmut.osm.repository.SpecificationOptionRepository;
import com.xmut.osm.repository.SpecificationRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 阮胜
 * @date 2018/7/30 23:38
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class InitData extends BaseTest {
    @Autowired
    private SpecificationRepository specificationRepository;
    @Autowired
    private SpecificationOptionRepository specificationOptionRepository;

    @Test
    public void init() {
        insertSpecification();
        insertSpecificationOption();
    }


    @Test
    public void insertSpecification() {
        specificationRepository.save(new Specification("电视屏幕尺寸"));
        specificationRepository.save(new Specification("手机屏幕尺寸"));
        specificationRepository.save(new Specification("尺码"));
        specificationRepository.save(new Specification("网络"));
        specificationRepository.save(new Specification("机身内存"));
    }

    @Test
    public void insertSpecificationOption() {

        specificationRepository.findByName("尺码").ifPresent(specification -> {
            specificationOptionRepository.save(new SpecificationOption("165", specification, 1));
            specificationOptionRepository.save(new SpecificationOption("170", specification, 2));
            specificationOptionRepository.save(new SpecificationOption("175", specification, 3));
            specificationOptionRepository.save(new SpecificationOption("180", specification, 4));
        });
        specificationRepository.findByName("手机屏幕尺寸").ifPresent(specification -> {
            specificationOptionRepository.save(new SpecificationOption("4.5寸", specification, 1));
            specificationOptionRepository.save(new SpecificationOption("5寸", specification, 2));
            specificationOptionRepository.save(new SpecificationOption("5.5寸", specification, 3));
            specificationOptionRepository.save(new SpecificationOption("6寸", specification, 4));
            specificationOptionRepository.save(new SpecificationOption("6.5寸", specification, 5));
        });
        specificationRepository.findByName("电视屏幕尺寸").ifPresent(specification -> {
            specificationOptionRepository.save(new SpecificationOption("40英寸", specification, 1));
            specificationOptionRepository.save(new SpecificationOption("41英寸", specification, 2));
            specificationOptionRepository.save(new SpecificationOption("42英寸", specification, 3));
            specificationOptionRepository.save(new SpecificationOption("43英寸", specification, 4));
            specificationOptionRepository.save(new SpecificationOption("44英寸", specification, 5));
            specificationOptionRepository.save(new SpecificationOption("45英寸", specification, 6));

        });
    }
}
