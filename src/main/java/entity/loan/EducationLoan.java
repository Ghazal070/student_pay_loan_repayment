package entity.loan;

import entity.Term;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
@DiscriminatorValue("EducationLoan")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EducationLoan extends Loan {

    @OneToOne
    protected Term term;


}
