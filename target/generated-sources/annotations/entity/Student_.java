package entity;

import entity.enumration.Degree;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Student.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Student_ extends entity.BaseEntity_ {

	public static final String ENTRY_YEAR = "entryYear";
	public static final String LAST_NAME = "lastName";
	public static final String FATHER_NAME = "fatherName";
	public static final String NATIONAL_CODE = "nationalCode";
	public static final String ADDRESS = "address";
	public static final String IS_DORMITORY = "isDormitory";
	public static final String CITY = "city";
	public static final String STUDENT_NUMBER = "studentNumber";
	public static final String UNIVERSITY = "university";
	public static final String MOTHER_NAME = "motherName";
	public static final String DEGREE = "degree";
	public static final String CONTRACT_NUMBER = "contractNumber";
	public static final String BIRTH_DATE = "birthDate";
	public static final String IS_MARRIED = "isMarried";
	public static final String FIRST_NAME = "firstName";
	public static final String PASSWORD = "password";
	public static final String CERTIFICATE_NUMBER = "certificateNumber";
	public static final String USERNAME = "username";
	public static final String PARTNER_NATIONAL_CODE = "partnerNationalCode";

	
	/**
	 * @see entity.Student#entryYear
	 **/
	public static volatile SingularAttribute<Student, Integer> entryYear;
	
	/**
	 * @see entity.Student#lastName
	 **/
	public static volatile SingularAttribute<Student, String> lastName;
	
	/**
	 * @see entity.Student#fatherName
	 **/
	public static volatile SingularAttribute<Student, String> fatherName;
	
	/**
	 * @see entity.Student#nationalCode
	 **/
	public static volatile SingularAttribute<Student, String> nationalCode;
	
	/**
	 * @see entity.Student#address
	 **/
	public static volatile SingularAttribute<Student, String> address;
	
	/**
	 * @see entity.Student#isDormitory
	 **/
	public static volatile SingularAttribute<Student, Boolean> isDormitory;
	
	/**
	 * @see entity.Student#city
	 **/
	public static volatile SingularAttribute<Student, City> city;
	
	/**
	 * @see entity.Student#studentNumber
	 **/
	public static volatile SingularAttribute<Student, String> studentNumber;
	
	/**
	 * @see entity.Student#university
	 **/
	public static volatile SingularAttribute<Student, University> university;
	
	/**
	 * @see entity.Student#motherName
	 **/
	public static volatile SingularAttribute<Student, String> motherName;
	
	/**
	 * @see entity.Student#degree
	 **/
	public static volatile SingularAttribute<Student, Degree> degree;
	
	/**
	 * @see entity.Student#contractNumber
	 **/
	public static volatile SingularAttribute<Student, String> contractNumber;
	
	/**
	 * @see entity.Student#birthDate
	 **/
	public static volatile SingularAttribute<Student, String> birthDate;
	
	/**
	 * @see entity.Student#isMarried
	 **/
	public static volatile SingularAttribute<Student, Boolean> isMarried;
	
	/**
	 * @see entity.Student#firstName
	 **/
	public static volatile SingularAttribute<Student, String> firstName;
	
	/**
	 * @see entity.Student#password
	 **/
	public static volatile SingularAttribute<Student, String> password;
	
	/**
	 * @see entity.Student#certificateNumber
	 **/
	public static volatile SingularAttribute<Student, String> certificateNumber;
	
	/**
	 * @see entity.Student
	 **/
	public static volatile EntityType<Student> class_;
	
	/**
	 * @see entity.Student#username
	 **/
	public static volatile SingularAttribute<Student, String> username;
	
	/**
	 * @see entity.Student#partnerNationalCode
	 **/
	public static volatile SingularAttribute<Student, String> partnerNationalCode;

}

