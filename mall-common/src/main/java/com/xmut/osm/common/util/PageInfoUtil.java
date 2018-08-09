package com.xmut.osm.common.util;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.common.bean.PageInfo;
import org.springframework.data.domain.Page;

/**
 * @author 阮胜
 * @date 2018/8/9 14:50
 */
public class PageInfoUtil {
    public static PageInfo generate(PageBean pageBean, Page page) {
        return new PageInfo(pageBean.getPage(), page.getTotalElements(), page.getContent());
    }
}
