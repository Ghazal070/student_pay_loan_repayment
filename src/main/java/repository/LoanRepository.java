package repository;

import entity.City;
import entity.loan.Loan;

public interface LoanRepository<T extends Loan> extends BaseEntityRepository<T,Integer>  {
}
