package entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Department.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Department_ extends entity.BaseEntity_ {

	public static final String COURSE_LIST = "courseList";
	public static final String TITLE = "title";

	
	/**
	 * @see entity.Department#courseList
	 **/
	public static volatile SetAttribute<Department, Course> courseList;
	
	/**
	 * @see entity.Department#title
	 **/
	public static volatile SingularAttribute<Department, String> title;
	
	/**
	 * @see entity.Department
	 **/
	public static volatile EntityType<Department> class_;

}

