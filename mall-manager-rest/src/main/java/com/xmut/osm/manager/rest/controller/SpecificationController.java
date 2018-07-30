package com.xmut.osm.manager.rest.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.common.util.ResultVOUtil;
import com.xmut.osm.entity.Specification;
import com.xmut.osm.goods.feign.SpecificationServiceClient;
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
    private final SpecificationServiceClient specificationServiceClient;

    public SpecificationController(SpecificationServiceClient specificationServiceClient) {
        this.specificationServiceClient = specificationServiceClient;
    }

    @GetMapping("/all")
    public PageInfo<Specification> fetchSpecificationAll(PageBean pageBean) {
        return specificationServiceClient.fetchSepecificationAll(pageBean.getPage(), pageBean.getSize());
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody Specification specification, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.generateResultVO(bindingResult);
        }
        ResultVO<String> resultVO = new ResultVO<>();
        if (specification.getId() == null) {
            specification.setId(0);
        }
        try {
            resultVO.setSuccess(specificationServiceClient.save(specification));
        } catch (Exception e) {
            log.error(e.getMessage());
            resultVO.setSuccess(false);
            resultVO.setMessage(e.getMessage());
        }
        return resultVO;
    }
}
