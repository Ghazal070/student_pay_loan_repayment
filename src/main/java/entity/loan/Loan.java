package entity.loan;

import entity.BaseEntity;
import entity.Student;
import entity.enumration.Degree;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student", "term"})
})
@Inheritance
@NoArgsConstructor
@SuperBuilder
public  abstract class Loan extends BaseEntity<Integer> {
    @Column
    @NotBlank
    protected Integer amount;

    @ManyToOne
    @NotBlank
    protected Student student;

    //todo factory method

    public abstract void loanAmount();

}
