package entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Bank.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Bank_ extends entity.BaseEntity_ {

	public static final String NAME = "name";

	
	/**
	 * @see entity.Bank#name
	 **/
	public static volatile SingularAttribute<Bank, String> name;
	
	/**
	 * @see entity.Bank
	 **/
	public static volatile EntityType<Bank> class_;

}

