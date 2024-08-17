package service;

import entity.City;
import entity.Student;
import entity.loan.Loan;

public interface LoanService<T extends Loan> extends BaseEntityService<T,Integer> {

    Boolean isValidGetLoan(Student student);
}
