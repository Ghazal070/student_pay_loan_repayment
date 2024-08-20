package entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(CreditCard.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class CreditCard_ extends entity.BaseEntity_ {

	public static final String BANK = "bank";
	public static final String C_CV2 = "CCV2";
	public static final String BALANCE = "balance";
	public static final String CREDIT_CARD_NUMBER = "creditCardNumber";
	public static final String EXPIRATION_DATE = "expirationDate";

	
	/**
	 * @see entity.CreditCard#bank
	 **/
	public static volatile SingularAttribute<CreditCard, Bank> bank;
	
	/**
	 * @see entity.CreditCard#CCV2
	 **/
	public static volatile SingularAttribute<CreditCard, String> CCV2;
	
	/**
	 * @see entity.CreditCard#balance
	 **/
	public static volatile SingularAttribute<CreditCard, Integer> balance;
	
	/**
	 * @see entity.CreditCard#creditCardNumber
	 **/
	public static volatile SingularAttribute<CreditCard, String> creditCardNumber;
	
	/**
	 * @see entity.CreditCard
	 **/
	public static volatile EntityType<CreditCard> class_;
	
	/**
	 * @see entity.CreditCard#expirationDate
	 **/
	public static volatile SingularAttribute<CreditCard, LocalDate> expirationDate;

}

