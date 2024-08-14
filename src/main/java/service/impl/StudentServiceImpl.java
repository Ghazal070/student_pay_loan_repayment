package service.impl;


import entity.Student;
import repository.StudentRepository;
import service.StudentService;



public class StudentServiceImpl extends BaseEntityServiceImpl<StudentRepository, Student,Integer> implements StudentService {
    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public Student findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Student login(String username, String password) {
        if (!username.isBlank() && !password.isBlank()) return repository.login(username,password);
        return null;
    }


}
