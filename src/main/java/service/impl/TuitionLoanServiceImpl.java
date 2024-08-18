package service.impl;


import entity.Student;
import entity.loan.Loan;
import entity.loan.TuitionLoan;
import repository.LoanRepository;
import repository.TuitionLoanRepository;
import service.LoanService;
import service.TuitionLoanService;

import java.time.LocalDate;


public class TuitionLoanServiceImpl extends LoanServiceImpl<TuitionLoanRepository, TuitionLoan> implements TuitionLoanService {


    public TuitionLoanServiceImpl(TuitionLoanRepository repository) {
        super(repository);
    }

    @Override
    public Boolean isValidGetLoan(LocalDate currentDate) {
        return null;
    }
    @Override
    public Integer loanAmount(Student student) {
        Integer amount=null;
        switch (student.getDegree()) {
            case Associate:
            case ContinuousBachelor:
            case DiscontinuousBachelor: {
                amount = 1_900_000;
                break;
            }
            case DiscontinuousMaster:
            case ContinuousMaster:
            case ContinuousPhD:
            case DisContinuousPhD: {
                amount = 2_250_000;
                break;
            }
            case ProfessionalPHD:{
                amount = 2_600_000;
                break;
            }

        }
        return amount;
    }


}
