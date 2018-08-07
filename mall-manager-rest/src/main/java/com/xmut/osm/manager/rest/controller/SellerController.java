package com.xmut.osm.manager.rest.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.common.util.ControllerTemplate;
import com.xmut.osm.entity.Seller;
import com.xmut.osm.entity.Seller;
import com.xmut.osm.seller.feign.SellerServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/8/7 17:00
 */
@RestController
@RequestMapping("/seller")
@Slf4j
public class SellerController {
    private final ControllerTemplate<Seller> controllerTemplate;

    public SellerController(SellerServiceClient sellerServiceClient) {
        this.controllerTemplate = ControllerTemplate.getInstance(sellerServiceClient);
    }

    @GetMapping("/all")
    public PageInfo<Seller> fetchAll(PageBean pageBean) {
        return controllerTemplate.fetchAll(pageBean);
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody Seller seller, BindingResult bindingResult) {
        if (seller.getId() == null) {
            seller.setId(0);
        }
        return controllerTemplate.save(seller, bindingResult);
    }

    @DeleteMapping("/deleteAll")
    public ResultVO<List<Integer>> deleteAll(Integer[] ids) {
        return controllerTemplate.deleteAll(ids);
    }
    
}
