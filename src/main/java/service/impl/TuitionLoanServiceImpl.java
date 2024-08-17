package service.impl;


import entity.Student;
import entity.loan.Loan;
import entity.loan.TuitionLoan;
import repository.LoanRepository;
import repository.TuitionLoanRepository;
import service.LoanService;
import service.TuitionLoanService;


public class TuitionLoanServiceImpl extends LoanServiceImpl<TuitionLoanRepository, TuitionLoan> implements TuitionLoanService {


    public TuitionLoanServiceImpl(TuitionLoanRepository repository) {
        super(repository);
    }

    @Override
    public Boolean isValidGetLoan(Student student) {
        return null;
    }

}
