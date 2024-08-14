package entity;


import entity.enumration.Degree;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("Student")
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
    private String nationalCode;

    @Column
    private  String certificateNumber;

    @Column
    private LocalDate birthDate;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private  String studentNumber;

    @Column
    private Degree degree;

    @Column
    private Integer entryYear;

    @ManyToOne
    private University university;

    public Student() {
        this.username = nationalCode;
        this.password = password;
    }


//    public String generatePassword() {
//        Random random=new Random();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0;i<8;i++){
//            int nextInt = random.nextInt();
//            String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&*])(?=.*[0-9]).{8}$";
//
//            char  c = (char) nextInt;
//        }
//    }


}
