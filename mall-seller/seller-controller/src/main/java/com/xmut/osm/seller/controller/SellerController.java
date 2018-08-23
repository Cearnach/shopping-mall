package com.xmut.osm.seller.controller;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.common.enumeration.SellerStatusEnum;
import com.xmut.osm.common.util.PageInfoUtil;
import com.xmut.osm.common.util.ResultVOUtil;
import com.xmut.osm.entity.Seller;
import com.xmut.osm.exception.TargetEntityNotFound;
import com.xmut.osm.manager.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author 阮胜
 * @date 2018/8/6 20:59
 */
@RestController
@Slf4j
public class SellerController {
    private final SellerService sellerService;
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/all")
    public PageInfo fetchSellerAll(PageBean pageBean) {
        Page<Seller> sellerPage = sellerService.findAll(pageBean);
        return PageInfoUtil.generate(pageBean, sellerPage);
    }

    @GetMapping("/all/status")
    public PageInfo fetchUncheckedSellers(@Min(0) Integer statusCode, PageBean pageBean, BindingResult bindingResult) {
        SellerStatusEnum statusEnum = SellerStatusEnum.findByStatusCode(statusCode);
        assert statusEnum != null;
        if (bindingResult.hasErrors() || statusEnum.getStatusCode() == -1) {
            return null;
        }
        Page<Seller> sellerPage = sellerService.findByStatus(statusEnum, pageBean);
        return PageInfoUtil.generate(pageBean, sellerPage);
    }

    @GetMapping("/")

    @PostMapping("/save")
    public boolean save(@RequestBody Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return false;
        }
        sellerService.save(seller);
        return true;
    }

    @PostMapping("/register")
    public ResultVO register(Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.generateResultVO(bindingResult);
        }
        ResultVO<String> resultVO = new ResultVO<>();
        seller.setStatus(SellerStatusEnum.UNCHECKED.getStatusCode());
        sellerService.save(seller);
        resultVO.setSuccess(true);
        return resultVO;
    }

    @PutMapping("/update/status")
    public boolean updateStatus(@Min(0) Integer sellerId, @Min(0) Integer statusCode) throws TargetEntityNotFound {
        sellerService.updateStatusCode(sellerId, statusCode);
        return true;
    }

    @DeleteMapping("/deleteAll")
    public List<Integer> deleteAll(Integer[] ids) {
        return sellerService.deleteIn(ids);
    }

}
