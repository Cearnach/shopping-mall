package com.xmut.osm.seller.feign;

import com.xmut.osm.common.constraint.FeignClientConstraints;
import com.xmut.osm.common.util.BaseServiceClient;
import com.xmut.osm.entity.Seller;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 阮胜
 * @date 2018/8/7 16:39
 */
@FeignClient(name = FeignClientConstraints.SELELR_SERVICE_CLIENT_NAME)
public interface SellerServiceClient extends BaseServiceClient<Seller> {
}
