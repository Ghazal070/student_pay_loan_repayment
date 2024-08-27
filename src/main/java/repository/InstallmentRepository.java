package repository;

import entity.Installment;

import java.util.List;

public interface InstallmentRepository extends BaseEntityRepository<Installment,Integer>  {

    List<Installment> loadIsPayed(Boolean isPayed);
    List<Installment> loadAllInstallmentLoanIsPay(Integer loanId,Boolean isPayed);
}
