package service;

import entity.Term;
import entity.loan.EducationLoan;
import entity.loan.Loan;
import entity.loan.TuitionLoan;

import java.util.List;

public interface EducationLoanService extends LoanService<EducationLoan>{
    List<Loan> getLoanInCurrentTerm(Term currentTerm);
}
