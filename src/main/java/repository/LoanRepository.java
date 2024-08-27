package repository;

import entity.Installment;
import entity.Student;
import entity.Term;
import entity.loan.Loan;
import entity.loan.EducationLoan;

import java.util.List;

public interface LoanRepository<T extends Loan> extends BaseEntityRepository<T,Integer>  {
    List<T> findByLoanTypeStudent(String dType);

}
