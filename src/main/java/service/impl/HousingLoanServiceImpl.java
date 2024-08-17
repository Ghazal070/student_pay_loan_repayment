package service.impl;


import entity.Student;
import entity.loan.HousingLoan;
import repository.HousingLoanRepository;
import service.HousingLoanService;


public class HousingLoanServiceImpl extends LoanServiceImpl<HousingLoanRepository, HousingLoan> implements HousingLoanService {


    public HousingLoanServiceImpl(HousingLoanRepository repository) {
        super(repository);
    }

    @Override
    public Boolean isValidGetLoan(Student student) {
        return null;
    }

}
