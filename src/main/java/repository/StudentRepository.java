package repository;

import entity.BaseEntity;
import entity.Student;

import java.util.List;

public interface StudentRepository extends BaseEntityRepository<Student,Integer> {
     Student login(String username, String password);
    Student findByUsername(String username);

}
