package com.xmut.osm.repository.base;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author 阮胜
 * @date 2018/7/2 16:47
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T,ID>
        implements BaseRepository<T,ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }


}
