package repository.impl;

import entity.loan.Loan;
import entity.loan.TuitionLoan;
import entity.loan.TuitionLoan_;
import jakarta.persistence.EntityManager;
import repository.LoanRepository;
import repository.TuitionLoanRepository;

public class TuitionLoanRepositoryImpl extends LoanRepositoryImpl<TuitionLoan> implements TuitionLoanRepository {
    public TuitionLoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
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
