package entity;

import entity.enumration.AcceptanceType;
import entity.enumration.UniversityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class University extends BaseEntity<Integer> {
    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private UniversityType universityType;

    @Column
    @Enumerated(EnumType.STRING)
    private AcceptanceType acceptanceType;

    @OneToMany(mappedBy = "university")
    private Set<Student> students;


}
