package service.impl;


import entity.Student;
import entity.loan.Loan;
import repository.LoanRepository;
import service.LoanService;


public abstract class LoanServiceImpl<U extends LoanRepository<T>,T extends Loan>
        extends BaseEntityServiceImpl<U, T, Integer> implements LoanService<T> {

    public LoanServiceImpl(U repository) {
        super(repository);
    }
}
