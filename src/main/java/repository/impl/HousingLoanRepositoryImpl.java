package repository.impl;

import entity.loan.HousingLoan;
import entity.loan.HousingLoan_;
import jakarta.persistence.EntityManager;
import repository.HousingLoanRepository;
import util.AuthHolder;

public class HousingLoanRepositoryImpl extends LoanRepositoryImpl<HousingLoan> implements HousingLoanRepository {
    public HousingLoanRepositoryImpl(EntityManager entityManager, AuthHolder authHolder) {
        super(entityManager, authHolder);
    }

    @Override
    public Class<HousingLoan> getEntityClass() {
        return HousingLoan.class;
    }

    @Override
    public String getUniqueFieldName() {
        return HousingLoan_.ID;
    }
}
