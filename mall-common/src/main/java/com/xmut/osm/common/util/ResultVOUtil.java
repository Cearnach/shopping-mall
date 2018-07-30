package com.xmut.osm.common.util;

import com.xmut.osm.common.bean.ResultVO;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author: 阮胜
 * @date: 2018/7/30 13:26
 */
public class ResultVOUtil {

    public static ResultVO generateResultVO(BindingResult bindingResult) {
        ResultVO resultVO = new ResultVO<>();
        String field = bindingResult.getFieldError().getField();
        String msg = bindingResult.getFieldError().getDefaultMessage();
        resultVO.setSuccess(false);
        assert msg != null;
        resultVO.setMessage(field.concat(msg));
        return resultVO;
    }
}