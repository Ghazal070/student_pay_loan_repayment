package repository.impl;

import entity.Student;
import entity.Term;
import entity.loan.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.SneakyThrows;
import repository.LoanRepository;
import util.AuthHolder;
import entity.loan.EducationLoan;

import java.util.List;

public class LoanRepositoryImpl<T extends Loan> extends BaseEntityRepositoryImpl<T, Integer> implements LoanRepository<T> {
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

    @SneakyThrows
    @Override
    public List<T> findByLoanTypeStudent(String dType) {
        Class<T> tClass = (Class<T>) Class.forName("entity.loan." + dType);
        String query = "from " + tClass.getSimpleName() + " l where l.student.id=?1";
        TypedQuery<T> typedQuery = entityManager.createQuery(query,tClass);
        typedQuery.setParameter(1, authHolder.tokenId);
        return typedQuery.getResultList();
    }
}
