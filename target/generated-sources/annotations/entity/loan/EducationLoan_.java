package entity.loan;

import entity.Term;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EducationLoan.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class EducationLoan_ extends entity.BaseEntity_ {

	public static final String TERM = "term";

	
	/**
	 * @see entity.loan.EducationLoan#term
	 **/
	public static volatile SingularAttribute<EducationLoan, Term> term;
	
	/**
	 * @see entity.loan.EducationLoan
	 **/
	public static volatile EntityType<EducationLoan> class_;

}

