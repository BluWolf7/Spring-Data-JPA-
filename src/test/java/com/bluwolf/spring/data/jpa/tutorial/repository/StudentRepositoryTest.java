package com.bluwolf.spring.data.jpa.tutorial.repository;

import com.bluwolf.spring.data.jpa.tutorial.entity.Guardian;
import com.bluwolf.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;



    @Test
    @Disabled
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("shabbir@gmail.com")
                .firstName("shabbir")
                .lastName("Dawoodi")
//                .guardianName("Nikhil")
//                .guardianEmail("nikhil@gmail.com")
//                .guardianMobile("9999999999")
                .build();

        studentRepository.save(student);


    }

    @Test
    @Disabled
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Nikhil")
                .email("nikhil@gmail.com")
                .mobile("9999999999")
                .build();

        Student student = Student.builder()
                .firstName("Shivam")
                .lastName("Kumar")
                .emailId("shivam@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);


    }

    @Test
    @Disabled
    public void printAllStudents(){
        List<Student> studentList =
                studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    @Disabled
    public void printStudentByFirstName(){

        List<Student> students =
                studentRepository.findByFirstName("Shivam");
        System.out.println("Students : " +students);

    }

    @Test
    public void printStudentByFirstNameContaining(){

        List<Student> students =
                studentRepository.findByFirstNameContaining("Sh");
        System.out.println("Students : " +students);

    }

    @Test
    public void printStudentByLastNameNotNull(){

        List<Student> students =
                studentRepository.findByLastNameNotNull();
        System.out.println("Students : " +students);

    }

    @Test
    public void printStudentByGuardianName(){

        List<Student> students =
                studentRepository.findByGuardianName("Nikhil");
        System.out.println("Students : " +students);

    }
    @Test
    public void printStudentByFirstNameAndLastName(){

        Student student =
                studentRepository.findByFirstNameAndLastName("Shabbir", "Dawoodi");
        System.out.println("student : " +student);

    }

    @Test
    public void printStudentByEmailAddress(){

        Student student =
                studentRepository.getStudentByEmailAddress("shabbir@gmail.com");
        System.out.println("student : " +student);

    }

    @Test
    public void printStudentFirstNameByEmailAddress(){

        String student =
                studentRepository.getStudentFirstNameByEmailAddress("shivam@gmail.com");
        System.out.println("student : " +student);

    }

    @Test
    public void printStudentByEmailAddressNative(){

        Student student =
                studentRepository.getStudentByEmailAddressNative("shabbir@gmail.com");
        System.out.println("student : " +student);

    }

    @Test
    public void printStudentByEmailAddressNativeNamedParam(){
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam("shivam@gmail.com");
        System.out.println("Student is : " + student);
    }

    @Test
    public void updateStudentNameByEmailIdTest(){
        studentRepository.updateStudentNameByEmailId("shabbir dawoodi",
                "shabbir@gmail.com");
    }
}