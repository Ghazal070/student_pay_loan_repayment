package entity;

import entity.enumration.Universitytype;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Teacher.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Teacher_ extends entity.Person_ {

	public static final String TEACHER_TYPE = "teacherType";
	public static final String COURSES_TEACHER = "coursesTeacher";
	public static final String BASE_SALARY = "baseSalary";

	
	/**
	 * @see entity.Teacher#teacherType
	 **/
	public static volatile SingularAttribute<Teacher, Universitytype> teacherType;
	
	/**
	 * @see entity.Teacher#coursesTeacher
	 **/
	public static volatile SetAttribute<Teacher, CourseTeacher> coursesTeacher;
	
	/**
	 * @see entity.Teacher#baseSalary
	 **/
	public static volatile SingularAttribute<Teacher, Integer> baseSalary;
	
	/**
	 * @see entity.Teacher
	 **/
	public static volatile EntityType<Teacher> class_;

}

