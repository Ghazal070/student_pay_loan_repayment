package entity.loan;

import entity.City;
import entity.Term;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SuperBuilder
public class HousingLoan extends Loan {

    @Override
    public void loanAmount() {
        if (student.getCity().getName().equals("Tehran")) {
            this.amount = 32_000_000;
        } else if (student.getCity().getIsBigCity()) {
            this.amount = 26_000_000;

        } else {
            this.amount = 19_500_000;
        }
    }

}
