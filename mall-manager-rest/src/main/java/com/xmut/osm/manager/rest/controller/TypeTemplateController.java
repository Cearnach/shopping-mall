package com.xmut.osm.manager.rest.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.common.util.ControllerTemplate;
import com.xmut.osm.common.util.ResultVOUtil;
import com.xmut.osm.dto.TypeTemplateDTO;
import com.xmut.osm.entity.TypeTemplate;
import com.xmut.osm.goods.feign.TypeTemplateClient;
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
    private final TypeTemplateClient typeTemplateClient;

    public TypeTemplateController(TypeTemplateClient typeTemplateClient) {
        this.typeTemplateClient = typeTemplateClient;
        controllerTemplate = ControllerTemplate.getInstance(typeTemplateClient);
    }

    @GetMapping("/all")
    public PageInfo<TypeTemplate> fetchAll(PageBean pageBean) {
        return controllerTemplate.fetchAll(pageBean);
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody TypeTemplateDTO typeTemplateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.generateResultVO(bindingResult);
        }
        if (typeTemplateDTO.getId() == null) {
            typeTemplateDTO.setId(0);
        }
        ResultVO<String> resultVO = new ResultVO<>();
        System.out.println(typeTemplateDTO);
        resultVO.setSuccess(typeTemplateClient.save(typeTemplateDTO));
        return resultVO;
    }

    @DeleteMapping("/deleteAll")
    public ResultVO<List<Integer>> deleteAll(Integer[] ids) {
        return controllerTemplate.deleteAll(ids);
    }

}
