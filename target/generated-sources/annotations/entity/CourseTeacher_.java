package entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CourseTeacher.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class CourseTeacher_ extends entity.BaseEntity_ {

	public static final String TEACHER = "teacher";
	public static final String COURSE_STUDENT = "courseStudent";
	public static final String COURSE = "course";
	public static final String TERM = "term";

	
	/**
	 * @see entity.CourseTeacher#teacher
	 **/
	public static volatile SingularAttribute<CourseTeacher, Teacher> teacher;
	
	/**
	 * @see entity.CourseTeacher#courseStudent
	 **/
	public static volatile SingularAttribute<CourseTeacher, CourseStudent> courseStudent;
	
	/**
	 * @see entity.CourseTeacher#course
	 **/
	public static volatile SingularAttribute<CourseTeacher, Course> course;
	
	/**
	 * @see entity.CourseTeacher#term
	 **/
	public static volatile SingularAttribute<CourseTeacher, Term> term;
	
	/**
	 * @see entity.CourseTeacher
	 **/
	public static volatile EntityType<CourseTeacher> class_;

}

