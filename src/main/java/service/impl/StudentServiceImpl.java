package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.StudentUpdate;
import entity.Student;
import repository.StudentRepository;
import service.DepartmentService;
import service.StudentService;

import java.util.Collections;
import java.util.List;

public class StudentServiceImpl extends PersonServiceImpl<StudentRepository, Student> implements StudentService {
    private final DepartmentService departmentService;
    private final ObjectMapper objectMapper;
    public StudentServiceImpl(StudentRepository repository, DepartmentService departmentService, ObjectMapper objectMapper) {
        super(repository);
        this.departmentService = departmentService;
        this.objectMapper = objectMapper;
    }

//    @Override
//    public List<Course> loadAllCourse(Integer uniqId) {
//        if (uniqId == null) throw new IllegalArgumentException("Unique ID cannot be null");
//        List<Course> courses = repository.loadAllCourse(uniqId);
//        return courses!=null ? courses: Collections.emptyList();
//    }

    @Override
    public List<Course> loadAllDepartmentCourse(Integer uniqId) {
        if (uniqId == null) throw new IllegalArgumentException("Unique ID cannot be null");
        List<Course> courses = repository.loadAllDepartmentCourse(uniqId);
        return courses != null ? courses : Collections.emptyList();
    }

    @Override
    public Student update(String newStringStudent,Student existEntity) {
        StudentUpdate studentUpdate;
        try {
           studentUpdate = objectMapper.readValue(newStringStudent, StudentUpdate.class);
           if (studentUpdate.firstName()!=null && !studentUpdate.firstName().isEmpty()){
               existEntity.setFirstName(studentUpdate.firstName());
           }
            if (studentUpdate.lastName()!=null && !studentUpdate.lastName().isEmpty()){
                existEntity.setLastName(studentUpdate.lastName());
            }
            if (studentUpdate.username()!=null && !studentUpdate.username().isEmpty()){
                existEntity.setUsername(studentUpdate.username());
            }
            if (studentUpdate.entryYear()!=null && studentUpdate.entryYear()!=0){
                existEntity.setEntryYear(studentUpdate.entryYear());
            }
            if (studentUpdate.uniqIdDepartment()!=null && !studentUpdate.uniqIdDepartment().isEmpty()){
                Department departmentServiceByUniqId = departmentService.findByUniqId(studentUpdate.uniqIdDepartment());
                if (departmentServiceByUniqId!=null) existEntity.setDepartment(departmentServiceByUniqId);
            }


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return repository.update(existEntity);

    }
}
