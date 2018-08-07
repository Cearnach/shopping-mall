package com.xmut.osm.manager.rest.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.common.util.ControllerTemplate;
import com.xmut.osm.common.util.ResultVOUtil;
import com.xmut.osm.entity.Brand;
import com.xmut.osm.goods.feign.BrandServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/22 23:34
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//@RestController
//@RequestMapping("/brand")
@Slf4j
public class BrandControllerBak {
    private final BrandServiceClient brandServiceClient;
    private final ControllerTemplate<Brand> controllerTemplate;

    public BrandControllerBak(BrandServiceClient brandServiceClient, ControllerTemplate<Brand> controllerTemplate) {
        this.brandServiceClient = brandServiceClient;
        this.controllerTemplate = controllerTemplate;
//        this.controllerTemplate.setBaseServiceClient(brandServiceClient);
    }


    @GetMapping("/all")
    public PageInfo<Brand> fetchBrandAll(PageBean pageBean) {
        return controllerTemplate.fetchAll(pageBean);
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody Brand brand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.generateResultVO(bindingResult);
        }
        ResultVO<String> resultVO = new ResultVO<>();
        if (brand.getId() == null) {
            brand.setId(0);
        }
        try {
            resultVO.setSuccess(brandServiceClient.save(brand));
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setMessage(e.getMessage());
        }
        return resultVO;
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
