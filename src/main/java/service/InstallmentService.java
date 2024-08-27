package service;

import entity.CreditCard;
import entity.Installment;
import entity.Student;
import entity.loan.Loan;

import java.util.List;

public interface InstallmentService extends BaseEntityService<Installment,Integer> {

    boolean createInstallment(Loan loan);
    double calculateInstallmentAmount(Integer loanAmount);
    List<Installment> loadIsPayed(Boolean isPayed);
    List<Installment> loadAllInstallmentLoanIsPay(Integer loanId,Boolean isPayed);
}
