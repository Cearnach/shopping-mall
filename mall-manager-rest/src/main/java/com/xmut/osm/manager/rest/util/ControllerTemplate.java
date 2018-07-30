package com.xmut.osm.manager.rest.util;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.common.util.ResultVOUtil;
import com.xmut.osm.entity.Brand;
import com.xmut.osm.goods.feign.BaseServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/30 15:38
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
@Slf4j
public class ControllerTemplate<T> {
    private BaseServiceClient<T> baseServiceClient;

    public void setBaseServiceClient(BaseServiceClient<T> baseServiceClient) {
        this.baseServiceClient = baseServiceClient;
    }

    public PageInfo<T> fetchAll(PageBean pageBean) {
        return baseServiceClient.fetchAll(pageBean.getPage(), pageBean.getSize());
    }

    public ResultVO save(@RequestBody Brand brand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.generateResultVO(bindingResult);
        }
        ResultVO<String> resultVO = new ResultVO<>();
        if (brand.getId() == null) {
            brand.setId(0);
        }
        try {
            resultVO.setSuccess(baseServiceClient.save(brand));
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setMessage(e.getMessage());
        }
        return resultVO;
    }

    public ResultVO<List<Integer>> deleteAll(Integer[] ids) {
        ResultVO<List<Integer>> resultVO = new ResultVO<>();
        try {
            List<Integer> failedIdList = baseServiceClient.deleteAll(ids);
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
