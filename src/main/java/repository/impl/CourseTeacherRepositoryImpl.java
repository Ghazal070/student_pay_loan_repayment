package repository.impl;

import entity.CourseTeacher_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.CourseTeacherRepository;

import java.util.List;

public class CourseTeacherRepositoryImpl extends BaseEntityRepositoryImpl<CourseTeacher,Integer> implements CourseTeacherRepository {
    public CourseTeacherRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<CourseTeacher> getEntityClass() {
        return CourseTeacher.class;
    }

    @Override
    public String getUniqueFieldName() {
        return CourseTeacher_.UNIQ_ID;
    }

    @Override
    public List<CourseTeacher> findByTeacher(Teacher teacher) {
        String query = "from CourseTeacher ct where ct.teacher.id =?1 ";
        TypedQuery<CourseTeacher> typedQuery = entityManager.createQuery(query, CourseTeacher.class);
        typedQuery.setParameter(1,teacher.getId());
        return typedQuery.getResultList();

    }
}
