package com.xmut.osm.service.base;

import com.xmut.osm.exception.TargetEntityNotFound;
import com.xmut.osm.form.PageBean;
import com.xmut.osm.repository.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/2 19:35
 */
@Transactional(rollbackFor = Exception.class)
public class BaseServiceImpl<Entity, ID extends Serializable, Repository extends BaseRepository<Entity, ID>> implements BaseService<Entity, ID> {
    @Autowired
    private Repository repository;


    @Override
    public Entity findById(ID id) throws TargetEntityNotFound {
        return repository.findById(id).orElseThrow(TargetEntityNotFound::new);
    }

    @Override
    public Entity save(Entity entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(ID id) throws TargetEntityNotFound {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public Page<Entity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Entity> findAll(PageBean pageBean) {
        return repository.findAll(PageRequest.of(pageBean.getPage(), pageBean.getSize()));
    }

    @Override
    public Page<Entity> findAll(PageBean pageBean, Sort.Direction direction, String... properties) {
        return repository.findAll(PageRequest.of(pageBean.getPage(), pageBean.getSize(), Sort.by(direction, properties)));
    }

    @Override
    public void deleteIn(ID[] idArr) {
        for (ID id : idArr) {
            try {
                delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<Entity> findAllById(List<ID> ids) {
        return repository.findAllById(ids);
    }
}
