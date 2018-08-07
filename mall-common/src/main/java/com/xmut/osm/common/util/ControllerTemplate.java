package com.xmut.osm.common.util;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 阮胜
 * @date 2018/7/30 15:38
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
public class ControllerTemplate<T> {
    private BaseServiceClient<T> baseServiceClient;
    private static Map<String, ControllerTemplate> clientMap = new HashMap<>();

    private ControllerTemplate() {

    }

    private ControllerTemplate(BaseServiceClient<T> baseServiceClient) {
        this.baseServiceClient = baseServiceClient;
    }

    public static ControllerTemplate getInstance(BaseServiceClient baseServiceClient) {
        String key = baseServiceClient.getClass().getSimpleName();
        ControllerTemplate controllerTemplate;
        if (clientMap.containsKey(key)) {
            controllerTemplate = clientMap.get(key);
        } else {
            //这里为了提高效率,使用双重判断.
            synchronized (ControllerTemplate.class) {
                if (clientMap.containsKey(key)) {
                    controllerTemplate = clientMap.get(key);
                } else {
                    controllerTemplate = new ControllerTemplate(baseServiceClient);
                    clientMap.put(key, controllerTemplate);
                }
            }
        }
        return controllerTemplate;
    }


    public PageInfo<T> fetchAll(PageBean pageBean) {
        return baseServiceClient.fetchAll(pageBean.getPage(), pageBean.getSize());
    }

    public ResultVO save(@RequestBody T entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.generateResultVO(bindingResult);
        }
        ResultVO<String> resultVO = new ResultVO<>();
        try {
            resultVO.setSuccess(baseServiceClient.save(entity));
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
