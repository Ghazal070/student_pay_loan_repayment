package repository.impl;

import entity.Course_;
import jakarta.persistence.EntityManager;
import repository.CourseRepository;

public class CourseRepositoryImpl extends BaseEntityRepositoryImpl<Course,Integer> implements CourseRepository {
    public CourseRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }

    @Override
    public String getUniqueFieldName() {
        return Course_.UNIQ_ID;
    }
}
