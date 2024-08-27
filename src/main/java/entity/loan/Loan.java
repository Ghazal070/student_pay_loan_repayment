package entity.loan;

import entity.BaseEntity;
import entity.Student;
import entity.enumration.Degree;
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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student", "term","dtype"})
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor
@SuperBuilder
public class Loan extends BaseEntity<Integer> {
    @Column
    @NotNull
    protected Integer amount;

    @ManyToOne
    @NotNull
    protected Student student;


    //todo factory method


    @Override
    public String toString() {
        return id +"- "+ "amount:" + amount + " studentName=" + student.getLastName();
    }
}
