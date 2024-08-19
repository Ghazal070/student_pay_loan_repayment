package repository;

import entity.Student;
import entity.Term;
import entity.loan.EducationLoan;
import entity.loan.HousingLoan;
import entity.loan.TuitionLoan;

import java.util.List;

public interface HousingLoanRepository extends LoanRepository<HousingLoan>  {
    List<HousingLoan> getHousingLoan(Student student);
}
