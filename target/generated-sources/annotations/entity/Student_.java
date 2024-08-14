package entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Student.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Student_ extends entity.Person_ {

	public static final String ENTRY_YEAR = "entryYear";
	public static final String COURSE_STUDENTS = "courseStudents";
	public static final String DEPARTMENT = "department";

	
	/**
	 * @see entity.Student#entryYear
	 **/
	public static volatile SingularAttribute<Student, Integer> entryYear;
	
	/**
	 * @see entity.Student#courseStudents
	 **/
	public static volatile SetAttribute<Student, CourseStudent> courseStudents;
	
	/**
	 * @see entity.Student#department
	 **/
	public static volatile SingularAttribute<Student, Department> department;
	
	/**
	 * @see entity.Student
	 **/
	public static volatile EntityType<Student> class_;

}

