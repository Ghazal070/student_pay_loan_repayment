package service.impl;

import entity.BaseEntity;
import repository.BaseEntityRepository;

import java.io.Serializable;
import java.util.List;

public class BaseEntityServiceImpl<U extends BaseEntityRepository<T,ID>
        ,T extends BaseEntity<ID>,ID extends Serializable> implements  BaseEntityRepository<T,ID>{


    protected final U repository;

    public BaseEntityServiceImpl(U repository) {
        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        T existEntity = repository.findByUniqId(entity.getUniqId());
        if (existEntity==null)return repository.save(entity);
        else return existEntity;
    }

    @Override
    public T update(T newEntity) {
        return repository.update(newEntity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
    public Boolean containById(Integer id){
        return repository.containById(id);
    }

    @Override
    public T findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<T> loadAll() {
        return repository.loadAll();
    }

    @Override
    public Boolean contain(T entity) {
        return repository.contain(entity);
    }
    @Override
    public T findByUniqId(String uniqId) {
        if (uniqId!=null) return repository.findByUniqId(uniqId);
        else return null;
    }
    public Boolean deleteByUniqId(String uniqId) {
        return repository.deleteByUniqId(uniqId);
    }

}
