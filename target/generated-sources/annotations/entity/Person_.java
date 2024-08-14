package entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Person.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Person_ extends entity.BaseEntity_ {

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String PASSWORD = "password";
	public static final String USERNAME = "username";

	
	/**
	 * @see entity.Person#firstName
	 **/
	public static volatile SingularAttribute<Person, String> firstName;
	
	/**
	 * @see entity.Person#lastName
	 **/
	public static volatile SingularAttribute<Person, String> lastName;
	
	/**
	 * @see entity.Person#password
	 **/
	public static volatile SingularAttribute<Person, String> password;
	
	/**
	 * @see entity.Person
	 **/
	public static volatile EntityType<Person> class_;
	
	/**
	 * @see entity.Person#username
	 **/
	public static volatile SingularAttribute<Person, String> username;

}

