package repository.impl;

import entity.loan.Loan;
import jakarta.persistence.EntityManager;
import repository.LoanRepository;

public  class LoanRepositoryImpl<T extends Loan> extends BaseEntityRepositoryImpl<T,Integer> implements LoanRepository<T> {
    public LoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<T> getEntityClass() {
        return null;
    }

    @Override
    public String getUniqueFieldName() {
        return null;
    }
}
