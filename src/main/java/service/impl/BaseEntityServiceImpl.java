package service.impl;

import entity.BaseEntity;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import repository.BaseEntityRepository;

import java.io.Serializable;
import java.util.List;

public class BaseEntityServiceImpl<U extends BaseEntityRepository<T, ID>
        , T extends BaseEntity<ID>, ID extends Serializable> implements BaseEntityRepository<T, ID> {

    protected final Validator validator;
    protected final U repository;
    ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();

    public BaseEntityServiceImpl(U repository) {
        this.repository = repository;
        this.validator = factory.getValidator();
    }

    @Override
    public T save(T entity) {
        T existEntity = null;
        if (entity.getId() != null) {
            existEntity = repository.findById(entity.getId());
        }
        if (existEntity == null) return repository.save(entity);
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

    public Boolean containById(Integer id) {
        return repository.containById(id);
    }

    @Override
    public T findById(ID id) {
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
        if (uniqId != null) return repository.findByUniqId(uniqId);
        else return null;
    }

    public Boolean deleteByUniqId(String uniqId) {
        return repository.deleteByUniqId(uniqId);
    }

}
