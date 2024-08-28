package service.impl;


import entity.Student;
import entity.Term;
import entity.loan.EducationLoan;
import repository.EducationLoanRepository;
import service.EducationLoanService;
import service.LoanService;
import service.TermService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


public class EducationLoanServiceImpl extends LoanServiceImpl<EducationLoanRepository, EducationLoan> implements EducationLoanService {

private final TermService termService;
private final LoanService loanService;
    public EducationLoanServiceImpl(EducationLoanRepository repository, TermService termService, LoanService loanService) {
        super(repository);
        this.termService = termService;
        this.loanService = loanService;
    }

    @Override
    public Boolean isValidGetLoan(LocalDate currentDate) {
        List<Term> terms = termService.loadAll();
        terms.sort(Comparator.comparing(Term::getTitle));
        Term currentTerm = terms.get(terms.size() - 1);
        String termTitle = loanService.convertDateToTitleTerm(currentDate);
        if (currentTerm.getTitle().equals(termTitle)){
            List<EducationLoan> loans = repository.getLoanInCurrentTerm(currentTerm);
            if (loans.size()==0){
                return true;
            }
        }
        return false;
    }


    @Override
    public Integer loanAmount(Student student) {
        Integer amount = null;
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
            case ProfessionalPHD: {
                amount = 2_600_000;
                break;
            }

        }
        return amount;
    }

    @Override
    public List<EducationLoan> getLoanInCurrentTerm(Term currentTerm) {
        return repository.getLoanInCurrentTerm(currentTerm);
    }
}
