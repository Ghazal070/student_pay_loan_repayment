package service.impl;


import entity.Student;
import entity.Term;
import entity.loan.Loan;
import repository.LoanRepository;
import service.LoanService;

import java.time.LocalDate;


public class LoanServiceImpl<U extends LoanRepository<T>, T extends Loan>
        extends BaseEntityServiceImpl<U, T, Integer> implements LoanService<T> {

    public LoanServiceImpl(U repository) {
        super(repository);
    }

    @Override
    public Boolean isValidGetLoan(Student student) {
        return null;
    }

    @Override
    public Boolean isAppropriateDate(LocalDate currentDate, Term studentTerm) {
        int year = currentDate.getYear();
        if (studentTerm.getTitle() != null) {
            char term = studentTerm.getTitle().charAt(studentTerm.getTitle().length() - 1);
            if (term == '1') {
                LocalDate start = LocalDate.of(year, 8, 1);
                LocalDate end = LocalDate.of(year, 8, 7);
                if (currentDate.isAfter(start) && currentDate.isBefore(end)) {
                    return true;
                }
            } else if (term == '2') {
                LocalDate start = LocalDate.of(year, 11, 25);
                LocalDate end = LocalDate.of(year, 12, 2);
                if (currentDate.isAfter(start) && currentDate.isBefore(end)) {
                    return true;
                }
            }
        }
        return false;

    }

}
