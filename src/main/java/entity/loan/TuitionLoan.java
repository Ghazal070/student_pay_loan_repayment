package entity.loan;

import entity.Term;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@DiscriminatorValue("TuitionLoan")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TuitionLoan extends Loan {

    @ManyToOne
    @NotNull
    protected Term term;


}
