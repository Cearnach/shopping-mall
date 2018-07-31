package com.xmut.osm.service.base;

import com.xmut.osm.common.bean.PageBean;
import com.xmut.osm.exception.TargetEntityNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/2 19:45
 */
public interface BaseService<T, ID extends Serializable> {
    T findById(ID id) throws TargetEntityNotFound;

    T save(T t);

    void delete(ID id) throws TargetEntityNotFound;

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(PageBean pageBean);

    Page<T> findAll(PageBean pageBean, Sort.Direction direction, String... properties);

    List<ID> deleteIn(ID[] idArr);

    long count();

    List<T> findAllById(List<ID> ids);
}
