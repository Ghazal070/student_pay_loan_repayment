package service.impl;


import entity.Student;
import entity.Term;
import entity.loan.Loan;
import repository.LoanRepository;
import service.LoanService;

import java.time.LocalDate;
import java.util.List;


public class LoanServiceImpl<U extends LoanRepository<T>, T extends Loan>
        extends BaseEntityServiceImpl<U, T, Integer> implements LoanService<T> {

    public LoanServiceImpl(U repository) {
        super(repository);
    }

    @Override
    public Boolean isValidGetLoan(LocalDate currentDate) {
        return null;
    }

    @Override
    public Boolean isAppropriateDate(LocalDate currentDate) {
        int year = currentDate.getYear();
        LocalDate start1 = LocalDate.of(year, 8, 1);
        LocalDate end1 = LocalDate.of(year, 8, 7);
        LocalDate start2 = LocalDate.of(year, 11, 25);
        LocalDate end2 = LocalDate.of(year, 12, 2);

        if ((currentDate.isAfter(start1) && currentDate.isBefore(end1)) ||
                (currentDate.isAfter(start2) && currentDate.isBefore(end2))) {
            return true;
        }
        return false;
    }

    @Override
    public String convertDateToTitleTerm(LocalDate currentDate) {
        StringBuilder titleTerm =new StringBuilder();
        titleTerm.append(currentDate.getYear());
        int month = currentDate.getMonthValue();
        if (month<=10 && month>=6) titleTerm.append("-1");
        if (month>10 && month<6)titleTerm.append("-2");
        return String.valueOf(titleTerm);
    }

    @Override
    public Integer loanAmount(Student student) {
        return null;
    }
}
