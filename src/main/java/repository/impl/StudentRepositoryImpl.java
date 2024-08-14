package repository.impl;

import entity.Student;
import entity.Student_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import repository.StudentRepository;

import java.util.List;

public class StudentRepositoryImpl extends BaseEntityRepositoryImpl<Student,Integer> implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public String getUniqueFieldName() {
        return Student_.ID;
    }

    @Override
    public Student login(String username, String password) {
        String query = """
                from %s p where p.username= ?1 and p.password= ?2
                """.formatted(getEntityClass().getName());
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, getEntityClass());
        typedQuery.setParameter(1, username);
        typedQuery.setParameter(2, password);
        List<Student> resultList = typedQuery.getResultList();
        if(!resultList.isEmpty()) return resultList.get(0);
        return null;
    }

    @Override
    public Student findByUsername(String username) {
        String query = """
                from %s p where p.username= ?1
                """.formatted(getEntityClass().getName());
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, getEntityClass());
        typedQuery.setParameter(1, username);
        List<Student> resultList = typedQuery.getResultList();
        if(!resultList.isEmpty()) return resultList.get(0);
        return null;
    }
}
