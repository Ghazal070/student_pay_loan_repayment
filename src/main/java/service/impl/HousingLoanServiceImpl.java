package service.impl;


import entity.Student;
import entity.loan.HousingLoan;
import repository.HousingLoanRepository;
import service.HousingLoanService;

import java.time.LocalDate;


public class HousingLoanServiceImpl extends LoanServiceImpl<HousingLoanRepository, HousingLoan> implements HousingLoanService {


    public HousingLoanServiceImpl(HousingLoanRepository repository) {
        super(repository);
    }

    @Override
    public Boolean isValidGetLoan(LocalDate currentDate) {
        return null;
    }
    @Override
    public Integer loanAmount(Student student) {
        Integer amount=null;
        if (student.getCity().getName().equals("Tehran")) {
            amount = 32_000_000;
        } else if (student.getCity().getIsBigCity()) {
            amount = 26_000_000;

        } else {
            amount = 19_500_000;
        }
        return amount;
    }


}
