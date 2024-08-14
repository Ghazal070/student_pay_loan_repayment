package repository.impl;

import entity.Teacher_;
import jakarta.persistence.EntityManager;
import repository.TeacherRepository;

public class TeacherRepositoryImpl extends PersonRepositoryImpl<Teacher> implements TeacherRepository {
    public TeacherRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }

    @Override
    public String getUniqueFieldName() {
        return Teacher_.UNIQ_ID;
    }


}
