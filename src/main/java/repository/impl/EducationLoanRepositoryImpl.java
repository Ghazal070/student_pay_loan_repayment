package repository.impl;

import entity.Term;
import entity.loan.EducationLoan;
import entity.loan.EducationLoan_;
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

    public List<EducationLoan> getLoanInCurrentTerm(Term currentTerm) {
        String query = "from EducationLoan  l where l.student.id=?1 and l.term.id =?2 ";
        TypedQuery<EducationLoan> query1 = entityManager.createQuery(query, EducationLoan.class);
        query1.setParameter(1,authHolder.tokenId);
        query1.setParameter(2,currentTerm.getId());
        List<EducationLoan> resultList = query1.getResultList();
       resultList.forEach(System.out::println);
        return query1.getResultList();
    }
}
