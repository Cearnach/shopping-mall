package com.xmut.osm.manager.rest.advice;

import com.xmut.osm.common.bean.ResultVO;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 阮胜
 * @date 2018/7/30 13:56
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO<String> defaultExceptionHandler(HttpServletRequest req, Exception e) {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setSuccess(false);
        if (!StringUtils.isEmpty(e.getCause())) {
            resultVO.setMessage(e.getCause().getMessage());
        } else {
            resultVO.setMessage(e.getMessage());
        }
        return resultVO;
    }
}
