package entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Course.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Course_ extends entity.BaseEntity_ {

	public static final String COURSE_TEACHERS = "courseTeachers";
	public static final String UNIT_COUNT = "unitCount";
	public static final String TITLE = "title";
	public static final String DEPARTMENT = "department";

	
	/**
	 * @see entity.Course#courseTeachers
	 **/
	public static volatile SetAttribute<Course, CourseTeacher> courseTeachers;
	
	/**
	 * @see entity.Course#unitCount
	 **/
	public static volatile SingularAttribute<Course, Integer> unitCount;
	
	/**
	 * @see entity.Course#title
	 **/
	public static volatile SingularAttribute<Course, String> title;
	
	/**
	 * @see entity.Course#department
	 **/
	public static volatile SingularAttribute<Course, Department> department;
	
	/**
	 * @see entity.Course
	 **/
	public static volatile EntityType<Course> class_;

}

