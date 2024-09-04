package entity;


import entity.enumration.Degree;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

@Getter
@Setter
@Entity
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
    @NotBlank(message = "NationalCode must not be Blank")
    @Size(max = 3, min = 3, message = "NationalCode number must be exactly 3 characters")
    @Pattern(regexp = "\\d{3}", message = "NationalCode must be exactly 3 digits")
    private String nationalCode;

    @Column
    @Size(max = 3, min = 1, message = "Certificate number must be between 1 and 8 characters")
    private  String certificateNumber;

    @Column
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Birth date must be in the format YYYY-MM-DD")
    private String birthDate;

    @Column(unique = true)
    @NotBlank(message = "username must not Blank")
    private String username;

    @Column
    @NotBlank(message = "password must not Blank")
    private String password;

    @Column
    @Size(max = 3 ,min = 3)
    private  String studentNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Column
    @Min(value = 1350, message = "start year must be greater than or equal to {value}")
    @Max(value = 1404,message = "start year must be smaller than or equal to {value}")
    private Integer entryYear;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    private University university;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    private City city;

    @Column
    private Boolean isDormitory;

    @Column
    private Boolean isMarried;

    @Column
    private String partnerNationalCode;

    @Column
    private String address;

    @Column
    private String contractNumber;

    @Override
    public String toString() {
        return "Student:" +id + "- "+firstName + lastName +" username="
                + username + " studentNumber=" + studentNumber + " entryYear=" + entryYear;

    }
}
