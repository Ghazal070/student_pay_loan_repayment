package entity.loan;

import entity.City;
import entity.Term;
import jakarta.persistence.DiscriminatorValue;
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
@DiscriminatorValue("HousingLoan")
@NoArgsConstructor
@SuperBuilder
public class HousingLoan extends Loan {

    @Override
    public String toString() {
        return "HousingLoan:" + super.toString();
    }
}
