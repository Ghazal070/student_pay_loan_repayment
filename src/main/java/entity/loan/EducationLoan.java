package entity.loan;

import entity.Term;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@DiscriminatorValue("EducationLoan")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class EducationLoan extends Loan {

    @ManyToOne
    @NotNull
    protected Term term;

    @Override
    public String toString() {
        return "EducationLoan: "+ "term=" + term + super.toString();
    }
}
