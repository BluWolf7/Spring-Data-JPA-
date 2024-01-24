package com.bluwolf.spring.data.jpa.tutorial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name= "course",
        uniqueConstraints = @UniqueConstraint(
                name = "title",
                columnNames = "title "
        )
)
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    @Column(
            name = "title",
            nullable = false
    )
    private String title;
    @Column(
            name = "credits",
            nullable = false
    )
    private Integer credits;

    /**Bi Directional One to One mapping, we only need to specify by which field we are defining join
     * in the reference class In this case it is "course" in CourseMaterial class
     */

    @OneToOne(
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name= "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    /**
     * Many-to-many relationship between courses and students
     * as multiple students can opt for multiple courses
     * in a many-to-many relationship there will be a third table tracking the relationship between the fields of the two tables in the relationship.
     */

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;

    //a method to add students to help builder.
    public void  addStudents(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);

    }


}
