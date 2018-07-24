package com.xmut.osm.manager.rest.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.entity.Brand;
import com.xmut.osm.goods.feign.BrandServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/22 23:34
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("/brand")
@Slf4j
public class BrandController {
    private final BrandServiceClient brandServiceClient;

    public BrandController(BrandServiceClient brandServiceClient) {
        this.brandServiceClient = brandServiceClient;
    }

    @GetMapping("/list")
    public PageInfo<Brand> fetchBrandList(PageBean pageBean) {
        return brandServiceClient.fetchBrandList(pageBean.getSize(), pageBean.getPage());
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Brand brand) {
        if (brand.getId() == null) {
            brand.setId(0);
        }
        return brandServiceClient.save(brand);
    }

    @DeleteMapping("/deleteAll")
    public ResultVO<List<Integer>> deleteAll(Integer[] ids) {
        ResultVO<List<Integer>> resultVO = new ResultVO<>();
        try {
            List<Integer> failedIdList = brandServiceClient.deleteAll(ids);
            if (CollectionUtils.isEmpty(failedIdList)) {
                resultVO.setSuccess(true);
            } else {
                resultVO.setSuccess(false);
                resultVO.setData(failedIdList);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            resultVO.setSuccess(false);
            resultVO.setMessage("发生错误:" + e.getMessage());
        }
        return resultVO;
    }
}
