package entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(City.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class City_ extends entity.BaseEntity_ {

	public static final String NAME = "name";
	public static final String IS_BIG_CITY = "isBigCity";

	
	/**
	 * @see entity.City#name
	 **/
	public static volatile SingularAttribute<City, String> name;
	
	/**
	 * @see entity.City
	 **/
	public static volatile EntityType<City> class_;
	
	/**
	 * @see entity.City#isBigCity
	 **/
	public static volatile SingularAttribute<City, Boolean> isBigCity;

}

