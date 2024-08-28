package service.impl;


import entity.Student;
import entity.Term;
import entity.loan.TuitionLoan;
import repository.TuitionLoanRepository;
import service.LoanService;
import service.StudentService;
import service.TermService;
import service.TuitionLoanService;
import util.AuthHolder;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


public class TuitionLoanServiceImpl extends LoanServiceImpl<TuitionLoanRepository, TuitionLoan> implements TuitionLoanService {

    private final TermService termService;
    private final LoanService loanService;
    private final AuthHolder authHolder;
    private final StudentService studentService;

    public TuitionLoanServiceImpl(TuitionLoanRepository repository, TermService termService, LoanService loanService, AuthHolder authHolder, StudentService studentService) {
        super(repository);
        this.termService = termService;
        this.loanService = loanService;
        this.authHolder = authHolder;
        this.studentService = studentService;
    }


    @Override
    public Boolean isValidGetLoan(LocalDate currentDate) {
        List<Term> terms = termService.loadAll();
        terms.sort(Comparator.comparing(Term::getTitle));
        Term currentTerm = terms.get(terms.size() - 1);
        String termTitle = loanService.convertDateToTitleTerm(currentDate);
        if (currentTerm.getTitle().equals(termTitle)) {
            List<TuitionLoan> loans = repository.getLoanInCurrentTerm(currentTerm);
            if (loans.size() == 0) {
                Student student = studentService.findById(authHolder.tokenId);
                boolean dolatiRuzane = student.getUniversity().getUniversityType().equals("DolatiRuzane");
                if (!dolatiRuzane) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Integer loanAmount(Student student) {
        Integer amount =null;
        switch (student.getDegree()) {
            case Associate:
            case ContinuousBachelor:
            case DiscontinuousBachelor: {
                amount = 1_300_000;
                break;
            }
            case DiscontinuousMaster:
            case ContinuousMaster:
            case ContinuousPhD:
            case DisContinuousPhD: {
                amount = 2_600_000;
                break;
            }
            case ProfessionalPHD:{
                amount = 6_500_000;
                break;
            }

        }
        return amount;
    }


}
