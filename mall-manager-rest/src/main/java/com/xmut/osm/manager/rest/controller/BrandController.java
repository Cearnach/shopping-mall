package com.xmut.osm.manager.rest.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.entity.Brand;
import com.xmut.osm.goods.feign.BrandServiceClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/22 23:34
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    private final BrandServiceClient brandServiceClient;

    public BrandController(BrandServiceClient brandServiceClient) {
        this.brandServiceClient = brandServiceClient;
    }

    @GetMapping("/list")
    public ResultVO<List<Brand>> fetchBrandList(PageBean pageBean) {
        List<Brand> brandList = brandServiceClient.fetchBrandList(pageBean.getSize(), pageBean.getPage());
        ResultVO<List<Brand>> resultVO = new ResultVO<>();
        resultVO.setSuccess(true);
        resultVO.setMessage("获取品牌列表成功");
        resultVO.setData(brandList);
        return resultVO;
    }
}
