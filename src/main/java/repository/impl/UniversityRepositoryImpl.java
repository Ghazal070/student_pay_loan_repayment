package repository.impl;

import entity.University;
import entity.University_;
import jakarta.persistence.EntityManager;
import repository.UniversityRepository;

public class UniversityRepositoryImpl extends BaseEntityRepositoryImpl<University,Integer> implements UniversityRepository {
    public UniversityRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<University> getEntityClass() {
        return University.class;
    }

    @Override
    public String getUniqueFieldName() {
        return University_.NAME;
    }


}
