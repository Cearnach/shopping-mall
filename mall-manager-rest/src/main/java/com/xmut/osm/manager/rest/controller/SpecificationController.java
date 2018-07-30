package com.xmut.osm.manager.rest.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.entity.Specification;
import com.xmut.osm.entity.SpecificationOption;
import com.xmut.osm.goods.feign.SpecificationServiceClient;
import com.xmut.osm.manager.rest.util.ControllerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

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
    private final SpecificationServiceClient specificationServiceClient;

    public SpecificationController(SpecificationServiceClient specificationServiceClient, SpecificationServiceClient specificationServiceClient1) {
        controllerTemplate = ControllerTemplate.getInstance(specificationServiceClient);
        this.specificationServiceClient = specificationServiceClient1;
    }

    @GetMapping("/all")
    public PageInfo<Specification> fetchSpecificationAll(PageBean pageBean) {
        return controllerTemplate.fetchAll(pageBean);
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody Specification specification, BindingResult bindingResult) {
        if (specification.getId() == null) {
            specification.setId(0);
        }

        System.out.println(specification);
        return null;
        //return controllerTemplate.save(specification, bindingResult);
    }

    @DeleteMapping("/deleteAll")
    public ResultVO<List<Integer>> deleteAll(Integer[] ids) {
        return controllerTemplate.deleteAll(ids);
    }

    @GetMapping("/option")
    private List<SpecificationOption> fetchOption(@Min(0) Integer specId) {
        return specificationServiceClient.fetchOption(specId);
    }
}
