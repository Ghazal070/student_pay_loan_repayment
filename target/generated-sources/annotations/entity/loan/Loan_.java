package entity.loan;

import entity.Student;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Loan.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Loan_ extends entity.BaseEntity_ {

	public static final String AMOUNT = "amount";
	public static final String STUDENT = "student";

	
	/**
	 * @see entity.loan.Loan#amount
	 **/
	public static volatile SingularAttribute<Loan, Integer> amount;
	
	/**
	 * @see entity.loan.Loan#student
	 **/
	public static volatile SingularAttribute<Loan, Student> student;
	
	/**
	 * @see entity.loan.Loan
	 **/
	public static volatile EntityType<Loan> class_;

}

