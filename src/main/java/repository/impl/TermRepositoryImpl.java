package repository.impl;

import entity.Term;
import entity.Term_;
import jakarta.persistence.EntityManager;
import repository.TermRepository;

public class TermRepositoryImpl extends BaseEntityRepositoryImpl<Term,Integer> implements TermRepository {
    public TermRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Term> getEntityClass() {
        return Term.class;
    }

    @Override
    public String getUniqueFieldName() {
        return Term_.TITLE;
    }


}
