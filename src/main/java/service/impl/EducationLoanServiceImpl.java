package service.impl;


import entity.Student;
import entity.Term;
import entity.loan.EducationLoan;
import entity.loan.Loan;
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
            List<Loan> loans = repository.getLoanInCurrentTerm(currentTerm);
            if (loans==null && loans.isEmpty()){
                return false;
            }
        }
        return true;
    }

}
