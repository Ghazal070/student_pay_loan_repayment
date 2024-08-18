package repository.impl;

import entity.loan.TuitionLoan;
import entity.loan.TuitionLoan_;
import jakarta.persistence.EntityManager;
import repository.TuitionLoanRepository;
import util.AuthHolder;

public class TuitionLoanRepositoryImpl extends LoanRepositoryImpl<TuitionLoan> implements TuitionLoanRepository {
    public TuitionLoanRepositoryImpl(EntityManager entityManager, AuthHolder authHolder) {
        super(entityManager, authHolder);
    }

    @Override
    public Class<TuitionLoan> getEntityClass() {
        return TuitionLoan.class;
    }

    @Override
    public String getUniqueFieldName() {
        return TuitionLoan_.ID;
    }
}
