package service.impl;


import entity.Student;
import entity.loan.HousingLoan;
import repository.HousingLoanRepository;
import service.HousingLoanService;
import service.LoanService;
import service.StudentService;
import service.TermService;
import util.AuthHolder;

import java.time.LocalDate;
import java.util.List;


public class HousingLoanServiceImpl extends LoanServiceImpl<HousingLoanRepository, HousingLoan> implements HousingLoanService {

    private final TermService termService;
    private final LoanService loanService;
    private final AuthHolder authHolder;
    private final StudentService studentService;

    public HousingLoanServiceImpl(HousingLoanRepository repository, TermService termService, LoanService loanService, AuthHolder authHolder, StudentService studentService) {
        super(repository);
        this.termService = termService;
        this.loanService = loanService;
        this.authHolder = authHolder;
        this.studentService = studentService;
    }

    @Override
    public Boolean isValidGetLoan(LocalDate currentDate) {
        Student student = studentService.findById(authHolder.tokenId);
        List<HousingLoan> housingLoanList = repository.getHousingLoan(student);
        if (housingLoanList.size() == 0) {
            if (!student.getIsDormitory()) {
                if (student.getIsMarried()) {
                    String partnerNationalCode = student.getPartnerNationalCode();
                    Student partnerStudent = studentService.findByUsername(partnerNationalCode);
                    if (partnerStudent == null || repository.getHousingLoan(partnerStudent).size() == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Integer loanAmount(Student student) {
        Integer amount = null;
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
