package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CreditCard extends BaseEntity<Integer>{

    @Column(unique = true)
    @Size(max=10,min = 10,message = "Credit card  must be 10 characters")
    private String creditCardNumber;

    @ManyToOne
    private Bank bank;

    @Column
    private Integer balance;

    @Column
    private LocalDate  expirationDate;

    @Column
    private String  CCV2;

    @ManyToOne
    @NotNull
    protected Student student;


}
