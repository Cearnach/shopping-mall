package com.xmut.osm.manager.rest.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.entity.TypeTemplate;
import com.xmut.osm.goods.feign.TypeTemplateClient;
import com.xmut.osm.manager.rest.util.ControllerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/31 14:42
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@Slf4j
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
    private final ControllerTemplate<TypeTemplate> controllerTemplate;

    public TypeTemplateController(TypeTemplateClient typeTemplateClient) {
        controllerTemplate = ControllerTemplate.getInstance(typeTemplateClient);
    }

    @GetMapping("/all")
    public PageInfo<TypeTemplate> fetchAll(PageBean pageBean) {
        return controllerTemplate.fetchAll(pageBean);
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody TypeTemplate typeTemplate, BindingResult bindingResult) {
        if (typeTemplate.getId() == null) {
            typeTemplate.setId(0);
        }
        return controllerTemplate.save(typeTemplate, bindingResult);
    }

    @DeleteMapping("/deleteAll")
    public ResultVO<List<Integer>> deleteAll(Integer[] ids) {
        return controllerTemplate.deleteAll(ids);
    }

}
