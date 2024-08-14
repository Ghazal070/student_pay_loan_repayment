package service;

public interface CourseService extends  BaseEntityService<Course,Integer>{
    Course update(String newStringStudent, Course existEntity);

}
