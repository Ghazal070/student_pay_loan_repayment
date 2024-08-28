package entity;

import entity.loan.Loan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Installment extends BaseEntity<Integer>{

    @Column
    private LocalDate localDate;

    @ManyToOne
    @NotNull
    private Loan loan;

    @Column
    private Integer amount;

    @Column
    private boolean isPayed;

    @Override
    public String toString() {
        return  id +"-" + " " +"loanType:" + loan.getClass().getSimpleName()+  " datePaying:" +localDate + " " +  "amount: " + amount ;
    }
}
