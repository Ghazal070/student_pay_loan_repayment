package repository;

import entity.Term;
import entity.loan.TuitionLoan;

import java.util.List;

public interface TuitionLoanRepository extends LoanRepository<TuitionLoan>  {

    List<TuitionLoan> getLoanInCurrentTerm(Term currentTerm);
}
