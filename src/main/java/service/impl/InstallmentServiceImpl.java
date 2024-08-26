package service.impl;


import entity.Installment;
import entity.Student;
import entity.loan.Loan;
import repository.InstallmentRepository;
import service.InstallmentService;
import service.StudentService;

import java.time.LocalDate;
import java.util.List;


public class InstallmentServiceImpl extends BaseEntityServiceImpl<InstallmentRepository, Installment, Integer> implements InstallmentService {

    private  final StudentService studentService;

    public InstallmentServiceImpl(InstallmentRepository repository, StudentService studentService) {
        super(repository);
        this.studentService = studentService;
    }

    @Override
    public boolean createInstallment(Loan loan) {
        LocalDate dateGraduated = studentService.dateGraduated();
        int payYear = 1;
        int i = 1;
        int totalInstallmentCreated=0;
        while (i < 6) {
            for (int j = 0; j < 12; j++) {
                dateGraduated=dateGraduated.plusDays(30);
                int amountInstallment =payYear*((int) calculateInstallmentAmount(loan.getAmount()));
                repository.save(
                        Installment.builder()
                                .loan(loan)
                                .localDate(dateGraduated)
                                .amount(amountInstallment)
                                .isPayed(false)
                                .build()
                );
                totalInstallmentCreated++;
            }
            payYear *= 2;
            i++;
        }
        return totalInstallmentCreated>0;
    }

    @Override
    public double calculateInstallmentAmount(Integer loanAmount) {
        double profit = 0.04;
        double loanWithProfit = loanAmount * (1 + profit);
//        12n+24n+48n+96n+16*12(192)n=372n
        return loanWithProfit / 372;
    }

    @Override
    public List<Installment> loadIsPayed(Boolean isPayed) {
        return repository.loadIsPayed(isPayed);
    }
}
