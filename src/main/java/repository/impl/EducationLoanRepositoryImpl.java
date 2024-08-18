package repository.impl;

import entity.Term;
import entity.loan.EducationLoan;
import entity.loan.EducationLoan_;
import entity.loan.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.EducationLoanRepository;
import util.AuthHolder;

import java.util.List;

public class EducationLoanRepositoryImpl extends LoanRepositoryImpl<EducationLoan> implements EducationLoanRepository {
    public EducationLoanRepositoryImpl(EntityManager entityManager, AuthHolder authHolder) {
        super(entityManager,authHolder);
    }

    @Override
    public Class<EducationLoan> getEntityClass() {
        return EducationLoan.class;
    }

    @Override
    public String getUniqueFieldName() {
        return EducationLoan_.ID;
    }
    @Override
    public List<Loan> getLoanInCurrentTerm(Term currentTerm) {
        String query = "from EducationLoan  l where l.student.id=?1 and l.term.id =?2 ";
        TypedQuery<Loan> query1 = entityManager.createQuery(query, Loan.class);
        query1.setParameter(1,authHolder.tokenId);
        query1.setParameter(2,currentTerm.getId());
        return query1.getResultList();
    }
}
