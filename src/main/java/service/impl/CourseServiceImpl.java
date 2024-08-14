package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CourseUpdate;
import repository.CourseRepository;
import service.CourseService;
import service.DepartmentService;

public class CourseServiceImpl extends BaseEntityServiceImpl<CourseRepository, Course,Integer>
        implements CourseService {
    private final DepartmentService departmentService;
    private final ObjectMapper objectMapper;
    public CourseServiceImpl(CourseRepository repository, DepartmentService departmentService, ObjectMapper objectMapper) {
        super(repository);
        this.departmentService = departmentService;
        this.objectMapper = objectMapper;
    }

    @Override
    public Course update(String newStringStudent, Course existEntity) {
        CourseUpdate courseUpdate;
        try {
            courseUpdate = objectMapper.readValue(newStringStudent, CourseUpdate.class);
            if (courseUpdate.title()!=null && !courseUpdate.title().isEmpty()){
                existEntity.setTitle(courseUpdate.title());
            }
            if (courseUpdate.unitCount()!=null && courseUpdate.unitCount()!=0){
                existEntity.setUnitCount(courseUpdate.unitCount());
            }
            if (courseUpdate.uniqIdDepartment()!=null && !courseUpdate.uniqIdDepartment().isEmpty()){
                Department departmentServiceByUniqId = departmentService.findByUniqId(courseUpdate.uniqIdDepartment());
                if (departmentServiceByUniqId!=null) existEntity.setDepartment(departmentServiceByUniqId);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return repository.update(existEntity);
    }


}
