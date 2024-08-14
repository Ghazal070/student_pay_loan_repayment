package repository.impl;

import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.CourseStudentRepository;

import java.util.List;
import java.util.Set;

public class CourseStudentRepositoryImpl extends BaseEntityRepositoryImpl<CourseStudent,Integer> implements CourseStudentRepository {
    public CourseStudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
    @Override
    public Class<CourseStudent> getEntityClass() {
        return CourseStudent.class;
    }

    @Override
    public String getUniqueFieldName() {
        return CourseStudent_.ID;
    }

    @Override
    public Double calculateAverageGrade(Student student, Term previousTerm) {
        String query = """  
            select cs.grade from CourseStudent cs   
            where cs.student.uniqId = ?1 and cs.term.title = ?2  
            """;
        TypedQuery<Double> typedQuery = entityManager.createQuery(query, Double.class);
        typedQuery.setParameter(1, student.getUniqId());
        typedQuery.setParameter(2, previousTerm.getTitle());

        List<Double> grades = typedQuery.getResultList();

        return grades.isEmpty() ? 0.0 : grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}
