package com.xmut.osm.manager.rest.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.entity.Specification;
import com.xmut.osm.goods.feign.SpecificationServiceClient;
import com.xmut.osm.manager.rest.util.ControllerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author 阮胜
 * @date 2018/7/30 15:16
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("/specification")
@Slf4j
public class SpecificationController {
    private final ControllerTemplate<Specification> controllerTemplate;

    public SpecificationController(SpecificationServiceClient specificationServiceClient) {
        controllerTemplate = ControllerTemplate.getInstance(specificationServiceClient);
    }

    @GetMapping("/all")
    public PageInfo<Specification> fetchSpecificationAll(PageBean pageBean) {
        return controllerTemplate.fetchAll(pageBean);
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody Specification specification, BindingResult bindingResult) {
        return controllerTemplate.save(specification, bindingResult);
    }
}
