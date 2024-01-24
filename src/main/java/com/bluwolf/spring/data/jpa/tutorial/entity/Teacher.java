package com.bluwolf.spring.data.jpa.tutorial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name= "teacher"
)
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long teacherId;
    @Column(
            name = "firstName",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "lastName",
            nullable = true
    )
    private String lastName;

    //One Teacher can teach many courses
    //Uni directional one-to-many relationship, every course will have a field teacher_id referencing this class.
    //JPA Always recommends to use many-to-one instead of one-to-many (in this case it would mean defining this relationship at the Course level instead)
//    @OneToMany(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            name = "teacher_id" ,
//            referencedColumnName = "teacherId"
//    )
//    private List<Course> courses;

}
