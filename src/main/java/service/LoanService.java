package service;

import entity.Student;
import entity.Term;
import entity.loan.Loan;

import java.time.LocalDate;
import java.util.List;

public interface LoanService<T extends Loan> extends BaseEntityService<T,Integer> {

    Boolean isValidGetLoan(LocalDate currentDate);
    Boolean isAppropriateDate(LocalDate currentDate);
    String convertDateToTitleTerm(LocalDate currentDate);
    Integer loanAmount(Student student);
    List<T> findByLoanTypeStudent(String dType);


}
