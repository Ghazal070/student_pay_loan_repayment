package repository;

import entity.Term;
import entity.loan.EducationLoan;
import entity.loan.Loan;

import java.util.List;

public interface EducationLoanRepository extends LoanRepository<EducationLoan>  {
    List<Loan> getLoanInCurrentTerm(Term currentTerm);

}
