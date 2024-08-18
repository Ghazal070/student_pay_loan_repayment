package repository.impl;

import entity.Bank;
import entity.Bank_;
import jakarta.persistence.EntityManager;
import repository.BankRepository;
import repository.impl.BaseEntityRepositoryImpl;

public class BankRepositoryImpl extends BaseEntityRepositoryImpl<Bank,Integer> implements BankRepository {
    public BankRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Bank> getEntityClass() {
        return Bank.class;
    }

    @Override
    public String getUniqueFieldName() {
        return Bank_.NAME;
    }


}
