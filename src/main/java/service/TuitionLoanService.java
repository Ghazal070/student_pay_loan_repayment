package service;

import entity.Student;
import entity.loan.Loan;
import entity.loan.TuitionLoan;

import java.time.LocalDate;

public interface TuitionLoanService extends LoanService<TuitionLoan>{
    Boolean isValidGetLoan(LocalDate currentDate);
}
