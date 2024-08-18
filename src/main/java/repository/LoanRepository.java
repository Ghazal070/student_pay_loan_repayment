package repository;

import entity.Term;
import entity.loan.Loan;

import java.util.List;

public interface LoanRepository<T extends Loan> extends BaseEntityRepository<T,Integer>  {

}
