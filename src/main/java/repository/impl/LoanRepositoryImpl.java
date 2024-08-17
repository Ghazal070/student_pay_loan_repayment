package repository.impl;

import entity.loan.Loan;
import jakarta.persistence.EntityManager;
import repository.LoanRepository;

public abstract class LoanRepositoryImpl<T extends Loan> extends BaseEntityRepositoryImpl<T,Integer> implements LoanRepository<T> {
    public LoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

}
