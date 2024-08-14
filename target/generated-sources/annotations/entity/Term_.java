package entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Term.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Term_ extends entity.BaseEntity_ {

	public static final String TITLE = "title";

	
	/**
	 * @see entity.Term#title
	 **/
	public static volatile SingularAttribute<Term, String> title;
	
	/**
	 * @see entity.Term
	 **/
	public static volatile EntityType<Term> class_;

}

