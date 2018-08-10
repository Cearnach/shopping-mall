package com.xmut.osm.goods.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.dto.TypeTemplateDTO;
import com.xmut.osm.entity.TypeTemplate;
import com.xmut.osm.goods.service.TypeTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/31 14:20
 */
@RestController
@Slf4j
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
    private final TypeTemplateService typeTemplateService;

    public TypeTemplateController(TypeTemplateService typeTemplateService) {
        this.typeTemplateService = typeTemplateService;
    }

    @GetMapping("/all")
    public PageInfo<TypeTemplate> fetchTypeTemplateAll(PageBean pageBean) {
        Page<TypeTemplate> typeTemplatePage = typeTemplateService.findAll(pageBean);
        return new PageInfo<>(pageBean.getPage(), typeTemplatePage.getTotalElements(), typeTemplatePage.getContent());
    }

    @PostMapping("/saveDTO")
    public boolean save(@RequestBody TypeTemplateDTO typeTemplateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return false;
        }
        typeTemplateService.save(typeTemplateDTO);
        return true;
    }

    @DeleteMapping("/deleteAll")
    public List<Integer> deleteAll(Integer[] ids) {
        return typeTemplateService.deleteIn(ids);
    }
}
