package repository.impl;

import entity.Installment;
import entity.Installment_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.InstallmentRepository;
import util.AuthHolder;

import java.util.List;

public class InstallmentRepositoryImpl extends BaseEntityRepositoryImpl<Installment,Integer> implements InstallmentRepository {

    private final AuthHolder authHolder;
    public InstallmentRepositoryImpl(EntityManager entityManager, AuthHolder authHolder) {
        super(entityManager);
        this.authHolder = authHolder;
    }

    @Override
    public Class<Installment> getEntityClass() {
        return Installment.class;
    }

    @Override
    public String getUniqueFieldName() {
        return Installment_.ID;
    }

    @Override
    public List<Installment> loadIsPayed(Boolean isPayed) {
        String query = "from Installment i where i.loan.student.id=?1 and i.isPayed=?2";
        TypedQuery<Installment> typedQuery = entityManager.createQuery(query, Installment.class);
        typedQuery.setParameter(1,authHolder.tokenId);
        typedQuery.setParameter(2,isPayed);
        return typedQuery.getResultList();
    }
}
