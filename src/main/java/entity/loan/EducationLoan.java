package entity.loan;

import entity.Term;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EducationLoan extends Loan {
    @OneToOne
    protected Term term;

    @Override
    public void loanAmount() {
        switch (student.getDegree()) {
            case Associate:
            case ContinuousBachelor:
            case DiscontinuousBachelor: {
                this.amount = 1_300_000;
                break;
            }
            case DiscontinuousMaster:
            case ContinuousMaster:
            case ContinuousPhD:
            case DisContinuousPhD: {
                this.amount = 2_600_000;
                break;
            }
            case ProfessionalPHD:{
                this.amount = 6_500_000;
                break;
            }

        }
    }

}
