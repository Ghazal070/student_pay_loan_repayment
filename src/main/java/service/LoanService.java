package service;

import entity.Student;
import entity.Term;
import entity.loan.Loan;

import java.time.LocalDate;

public interface LoanService<T extends Loan> extends BaseEntityService<T,Integer> {

    Boolean isValidGetLoan(Student student);
    Boolean isAppropriateDate(LocalDate currentDate, Term studentTerm);
}
