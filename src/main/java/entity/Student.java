package entity;


import entity.enumration.Degree;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("Student")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Student extends BaseEntity<Integer>{

    @Column
    private  String firstName;
    @Column
    private  String lastName;

    @Column
    private  String fatherName;

    @Column
    private  String motherName;

    @Column(unique = true)
    @NotBlank
    private String nationalCode;

    @Column
    @Size(max = 8 ,min = 1)
    private  String certificateNumber;

    @Column
    private LocalDate birthDate;

    @Column(unique = true)
    @NotBlank
    private String username;

    @Column
    @NotBlank
    private String password;

    @Column
    @Size(max = 3 ,min = 3)
    private  String studentNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Column
    @Min(value = 1980, message = "start year must be greater than or equal to {value}")
    @Max(value = 2024,message = "start year must be smaller than or equal to {value}")
    private Integer entryYear;

    @ManyToOne
    private University university;


}
