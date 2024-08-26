package entity;

import entity.loan.Loan;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Installment.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Installment_ extends entity.BaseEntity_ {

	public static final String LOAN = "loan";
	public static final String AMOUNT = "amount";
	public static final String LOCAL_DATE = "localDate";
	public static final String IS_PAYED = "isPayed";

	
	/**
	 * @see entity.Installment#loan
	 **/
	public static volatile SingularAttribute<Installment, Loan> loan;
	
	/**
	 * @see entity.Installment#amount
	 **/
	public static volatile SingularAttribute<Installment, Integer> amount;
	
	/**
	 * @see entity.Installment#localDate
	 **/
	public static volatile SingularAttribute<Installment, LocalDate> localDate;
	
	/**
	 * @see entity.Installment
	 **/
	public static volatile EntityType<Installment> class_;
	
	/**
	 * @see entity.Installment#isPayed
	 **/
	public static volatile SingularAttribute<Installment, Boolean> isPayed;

}

