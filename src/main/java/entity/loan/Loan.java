package entity.loan;

import entity.BaseEntity;
import entity.Student;
import entity.enumration.Degree;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student", "term"})
})
@Inheritance
@NoArgsConstructor
@SuperBuilder
public  abstract class Loan extends BaseEntity<Integer> {
    @Column
    protected Integer amount;

    @ManyToOne
    protected Student student;

    public abstract void loanAmount();

}
