package com.xmut.osm.seller.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.common.util.ControllerTemplate;
import com.xmut.osm.common.util.CookieUtil;
import com.xmut.osm.common.util.ResultVOUtil;
import com.xmut.osm.dto.GoodsDTO;
import com.xmut.osm.entity.Goods;
import com.xmut.osm.goods.feign.GoodsServiceClient;
import com.xmut.osm.security.property.JwtAuthenticationProperties;
import com.xmut.osm.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 阮胜
 * @date 2018/8/16 14:30
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    public static final String INVALID_TOKEN = "无效的Token";
    private final ControllerTemplate<Goods> controllerTemplate;
    private final JwtAuthenticationProperties jwtAuthenticationProperties;

    public GoodsController(GoodsServiceClient goodsServiceClient, JwtAuthenticationProperties jwtAuthenticationProperties) {
        this.controllerTemplate = ControllerTemplate.getInstance(goodsServiceClient);
        this.jwtAuthenticationProperties = jwtAuthenticationProperties;
    }

    @GetMapping("/all")
    public PageInfo<Goods> fetchAll(PageBean pageBean) {
        return controllerTemplate.fetchAll(pageBean);
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody Goods goods, BindingResult bindingResult) {
        return controllerTemplate.save(goods, bindingResult);
    }

    @PostMapping("/saveDTO")
    public ResultVO save(@RequestBody GoodsDTO goodsDTO, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.generateResultVO(bindingResult);
        }
        String account = fetUserAccount(request);
        if (StringUtils.isEmpty(account)) {
            return new ResultVO(INVALID_TOKEN, false, null);
        }
        goodsDTO.setSellerAccount(account);
        System.out.println(goodsDTO);
        return null;
    }

    private String fetUserAccount(HttpServletRequest request) {
        String token = CookieUtil.fetchCookie(request.getCookies(), jwtAuthenticationProperties.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader(jwtAuthenticationProperties.getHeader());
        }
        if (StringUtils.isEmpty(token) || !token.startsWith(jwtAuthenticationProperties.getPrefix())) {
            return null;

        }
        String jwtToken = token.replace(jwtAuthenticationProperties.getPrefix(), "");
        Claims claims = JwtTokenUtil.parse(jwtToken, jwtAuthenticationProperties.getSecret());
        //返回username
        return claims.getSubject();

    }

    @DeleteMapping("/deleteAll")
    public ResultVO<List<String>> deleteAll(String[] ids) {
        return null;
    }

}
