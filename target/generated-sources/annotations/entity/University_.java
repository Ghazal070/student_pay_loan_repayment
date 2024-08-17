package entity;

import entity.enumration.UniversityType;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(University.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class University_ extends entity.BaseEntity_ {

	public static final String UNIVERSITY_TYPE = "universityType";
	public static final String NAME = "name";
	public static final String STUDENTS = "students";

	
	/**
	 * @see entity.University#universityType
	 **/
	public static volatile SingularAttribute<University, UniversityType> universityType;
	
	/**
	 * @see entity.University#name
	 **/
	public static volatile SingularAttribute<University, String> name;
	
	/**
	 * @see entity.University#students
	 **/
	public static volatile SetAttribute<University, Student> students;
	
	/**
	 * @see entity.University
	 **/
	public static volatile EntityType<University> class_;

}

