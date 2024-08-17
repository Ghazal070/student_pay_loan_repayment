package repository.impl;

import entity.loan.EducationLoan;
import entity.loan.EducationLoan_;
import jakarta.persistence.EntityManager;
import repository.EducationLoanRepository;

public class EducationLoanRepositoryImpl extends LoanRepositoryImpl<EducationLoan> implements EducationLoanRepository {
    public EducationLoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<EducationLoan> getEntityClass() {
        return EducationLoan.class;
    }

    @Override
    public String getUniqueFieldName() {
        return EducationLoan_.ID;
    }
}
