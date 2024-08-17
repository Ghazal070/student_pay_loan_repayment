package service.impl;


import entity.Student;
import entity.loan.EducationLoan;
import repository.EducationLoanRepository;
import service.EducationLoanService;


public class EducationLoanServiceImpl extends LoanServiceImpl<EducationLoanRepository, EducationLoan> implements EducationLoanService {


    public EducationLoanServiceImpl(EducationLoanRepository repository) {
        super(repository);
    }

    @Override
    public Boolean isValidGetLoan(Student student) {

        return null;
    }

}
