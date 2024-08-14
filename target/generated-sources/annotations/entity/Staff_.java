package entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Staff.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Staff_ extends entity.Person_ {

	public static final String BASE_SALARY = "baseSalary";

	
	/**
	 * @see entity.Staff#baseSalary
	 **/
	public static volatile SingularAttribute<Staff, Double> baseSalary;
	
	/**
	 * @see entity.Staff
	 **/
	public static volatile EntityType<Staff> class_;

}

