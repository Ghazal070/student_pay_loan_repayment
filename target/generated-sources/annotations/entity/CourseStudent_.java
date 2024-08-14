package entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CourseStudent.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class CourseStudent_ extends entity.BaseEntity_ {

	public static final String STUDENT = "student";
	public static final String GRADE = "grade";
	public static final String TERM = "term";
	public static final String COURSE_TEACHER = "courseTeacher";

	
	/**
	 * @see entity.CourseStudent#student
	 **/
	public static volatile SingularAttribute<CourseStudent, Student> student;
	
	/**
	 * @see entity.CourseStudent#grade
	 **/
	public static volatile SingularAttribute<CourseStudent, Double> grade;
	
	/**
	 * @see entity.CourseStudent#term
	 **/
	public static volatile SingularAttribute<CourseStudent, Term> term;
	
	/**
	 * @see entity.CourseStudent#courseTeacher
	 **/
	public static volatile SingularAttribute<CourseStudent, CourseTeacher> courseTeacher;
	
	/**
	 * @see entity.CourseStudent
	 **/
	public static volatile EntityType<CourseStudent> class_;

}

