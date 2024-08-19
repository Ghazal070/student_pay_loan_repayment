package repository;

import entity.Term;
import entity.loan.Loan;
import entity.loan.TuitionLoan;

import java.util.List;

public interface TuitionLoanRepository extends LoanRepository<TuitionLoan>  {

    List<Loan> getLoanInCurrentTerm(Term currentTerm);
}
