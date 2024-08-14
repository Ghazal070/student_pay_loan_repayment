package service;

import entity.BaseEntity;
import entity.Student;

import java.util.List;

public interface StudentService extends BaseEntityService<Student,Integer> {

    Student findByUsername(String username);
    Student login(String username, String password);
}
