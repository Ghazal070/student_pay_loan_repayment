package repository;

import entity.Student;

public interface CourseStudentRepository extends BaseEntityRepository<CourseStudent,Integer>{
    Double calculateAverageGrade(Student student, Term previousTerm);
}
