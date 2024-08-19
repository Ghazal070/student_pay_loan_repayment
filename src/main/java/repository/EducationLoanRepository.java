package repository;

import entity.Term;
import entity.loan.EducationLoan;

import java.util.List;

public interface EducationLoanRepository extends LoanRepository<EducationLoan>  {
    List<EducationLoan> getLoanInCurrentTerm(Term currentTerm);

}
