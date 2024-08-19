package service;

import entity.Term;
import entity.loan.EducationLoan;

import java.util.List;

public interface EducationLoanService extends LoanService<EducationLoan>{
    List<EducationLoan> getLoanInCurrentTerm(Term currentTerm);
}
