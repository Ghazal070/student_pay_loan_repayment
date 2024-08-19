package repository.impl;

import entity.Term;
import entity.loan.TuitionLoan;
import entity.loan.TuitionLoan_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.TuitionLoanRepository;
import util.AuthHolder;

import java.util.List;

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

    @Override
    public List<TuitionLoan> getLoanInCurrentTerm(Term currentTerm) {
        String query = "from TuitionLoan  l where l.student.id=?1 and l.term.id =?2 ";
        TypedQuery<TuitionLoan> query1 = entityManager.createQuery(query, TuitionLoan.class);
        query1.setParameter(1,authHolder.tokenId);
        query1.setParameter(2,currentTerm.getId());
        return query1.getResultList();
    }
}
