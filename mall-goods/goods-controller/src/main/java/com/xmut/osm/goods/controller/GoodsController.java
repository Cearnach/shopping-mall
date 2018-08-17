package com.xmut.osm.goods.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.common.util.ResultVOUtil;
import com.xmut.osm.dto.GoodsDTO;
import com.xmut.osm.entity.Goods;
import com.xmut.osm.exception.TargetEntityNotFound;
import com.xmut.osm.goods.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品控制层
 *
 * @author 阮胜
 * @date 2018/7/21 15:55
 */
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/all")
    public PageInfo<Goods> fetchGoodsAll(PageBean pageBean) {
        Page<Goods> goodsPage = goodsService.findAll(pageBean);
        return new PageInfo<>(pageBean.getPage(), goodsPage.getTotalElements(), goodsPage.getContent());
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Goods goods) {
        goodsService.save(goods);
        return true;
    }

    @PostMapping("/saveDTO")
    public boolean save(@RequestBody GoodsDTO goodsDTO, BindingResult bindingResult) throws TargetEntityNotFound {
        if (bindingResult.hasErrors()) {
            return false;
        }
        goodsService.save(goodsDTO);
        return true;
    }

    @DeleteMapping("/deleteAll")
    public List<String> deleteAll(String[] ids) {
        return goodsService.deleteIn(ids);
    }

}
