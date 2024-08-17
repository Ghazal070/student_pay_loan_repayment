package entity.loan;

import entity.Term;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TuitionLoan.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class TuitionLoan_ extends entity.loan.Loan_ {

	public static final String TERM = "term";

	
	/**
	 * @see entity.loan.TuitionLoan#term
	 **/
	public static volatile SingularAttribute<TuitionLoan, Term> term;
	
	/**
	 * @see entity.loan.TuitionLoan
	 **/
	public static volatile EntityType<TuitionLoan> class_;

}

