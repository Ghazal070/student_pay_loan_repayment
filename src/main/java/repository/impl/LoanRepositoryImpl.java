package repository.impl;

import entity.Term;
import entity.loan.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.LoanRepository;
import util.AuthHolder;

import java.util.List;

public  class LoanRepositoryImpl<T extends Loan> extends BaseEntityRepositoryImpl<T,Integer> implements LoanRepository<T> {
    protected final AuthHolder authHolder;
    public LoanRepositoryImpl(EntityManager entityManager, AuthHolder authHolder) {
        super(entityManager);
        this.authHolder = authHolder;
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
