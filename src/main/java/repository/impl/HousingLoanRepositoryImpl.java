package repository.impl;

import entity.Student;
import entity.Term;
import entity.loan.EducationLoan;
import entity.loan.HousingLoan;
import entity.loan.HousingLoan_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.HousingLoanRepository;
import util.AuthHolder;

import java.util.List;

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

    @Override
    public List<HousingLoan> getHousingLoan(Student student) {
        String query = "from HousingLoan  l where l.student.id=?1 ";
        TypedQuery<HousingLoan> query1 = entityManager.createQuery(query, HousingLoan.class);
        query1.setParameter(1,student.getId());
        return query1.getResultList();
    }
}
