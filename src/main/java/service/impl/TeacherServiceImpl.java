package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TeacherUpdate;
import repository.TeacherRepository;
import service.TeacherService;
import util.AuthHolder;

import java.util.Set;

public class TeacherServiceImpl extends PersonServiceImpl<TeacherRepository, Teacher> implements TeacherService {
    private final ObjectMapper objectMapper;
    private final AuthHolder authHolder;
    public TeacherServiceImpl(TeacherRepository repository, ObjectMapper objectMapper, AuthHolder authHolder) {
        super(repository);
        this.objectMapper = objectMapper;
        this.authHolder = authHolder;
    }

    @Override
    public Teacher update(String newStringTeacher, Teacher existEntity) {
        TeacherUpdate teacherUpdate;
        try {
            teacherUpdate = objectMapper.readValue(newStringTeacher, TeacherUpdate.class);
            if (teacherUpdate.firstName()!=null && !teacherUpdate.firstName().isEmpty()){
                existEntity.setFirstName(teacherUpdate.firstName());
            }
            if (teacherUpdate.lastName()!=null && !teacherUpdate.lastName().isEmpty()){
                existEntity.setLastName(teacherUpdate.lastName());
            }
            if (teacherUpdate.username()!=null && !teacherUpdate.username().isEmpty()){
                existEntity.setUsername(teacherUpdate.username());
            }
            if (teacherUpdate.baseSalary()!=null && teacherUpdate.baseSalary()!=0){
                existEntity.setBaseSalary(teacherUpdate.baseSalary());
            }
            if (teacherUpdate.teacherType()!=null){
                existEntity.setTeacherType(teacherUpdate.teacherType());
            }


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return repository.update(existEntity);
    }

    @Override
    public Integer paySlipTuition(Term term) {
        Teacher teacher = repository.findByUsername(authHolder.tokenName);
        Integer sumUnit = getSumUnit(term, teacher);
        if (sumUnit != null) return sumUnit*1000000;
        return 0;
    }

    @Override
    public Integer paySlipScienceCommittee(Term term) {
        Teacher teacher = repository.findByUsername(authHolder.tokenName);
        Integer sumUnit = getSumUnit(term, teacher);
        if (sumUnit != null) return teacher.getBaseSalary() + (sumUnit*1000000);
        return 0;
    }
    public Integer getSumUnit(Term term, Teacher teacher) {
        Set<CourseTeacher> courseTeachers=teacher.getCoursesTeacher();
        if (courseTeachers!=null && !courseTeachers.isEmpty())
            return courseTeachers.stream().filter(courseTeacher ->
                courseTeacher.getTerm().equals(term)
        ).mapToInt(a -> a.getCourse().getUnitCount()).sum();
        return 0;
    }
}
