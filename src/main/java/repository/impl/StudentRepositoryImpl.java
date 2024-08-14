package repository.impl;

import entity.Student;
import entity.Student_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import repository.StudentRepository;

import java.util.List;

public class StudentRepositoryImpl extends PersonRepositoryImpl<Student> implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public String getUniqueFieldName() {
        return Student_.UNIQ_ID;
    }

    @Override
    public List<Course> loadAllCourse(Integer uniqId) {
        entityManager.getTransaction().begin();
//        String query = """
//                select c from Student s
//                join Department d
//                join Course c
//                where s.id = ?1
//                """;
//        List<Course> resultList = entityManager.createQuery(query, Course.class)
//                .setParameter(1, uniqId)
//                .getResultList();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        Root<Student> studentRoot = query.from(Student.class);
        Join<Student, Department> departmentJoin = studentRoot.join("department");
        Join<Department, Course> courseJoin = departmentJoin.join("course");
        query.select(courseJoin);
        query.where(
                criteriaBuilder.equal(studentRoot.get("id"), uniqId)
        );
        List<Course> resultList = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

    @Override
    public List<Course> loadAllDepartmentCourse(Integer uniqId) {
        //       entityManager.getTransaction().begin();
//        String query = """
//                select c from Student s
//                join Department d
//                join Course c
//                where s.id = ?1
//                """;
//        List<Course> resultList = entityManager.createQuery(query, Course.class)
//                .setParameter(1, uniqId)
//                .getResultList();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        Root<Student> studentRoot = query.from(Student.class);
        Join<Student, Department> departmentJoin = studentRoot.join("department");
        Join<Department, Course> courseJoin = departmentJoin.join("courseList");
        query.select(courseJoin);
        query.where(
                criteriaBuilder.equal(studentRoot.get("id"), uniqId)
        );
        return entityManager.createQuery(query).getResultList();
//        entityManager.getTransaction().commit();

    }
}
