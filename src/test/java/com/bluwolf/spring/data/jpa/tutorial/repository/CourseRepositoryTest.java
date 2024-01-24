package com.bluwolf.spring.data.jpa.tutorial.repository;

import com.bluwolf.spring.data.jpa.tutorial.entity.Course;
import com.bluwolf.spring.data.jpa.tutorial.entity.Student;
import com.bluwolf.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){

        List<Course> courses = courseRepository.findAll();
        System.out.println("courses =" + courses);

    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Priyanka")
                .lastName("Singh")
                .build();


        Course course = Course.builder()
                .title("Python")
                .credits(6)
                .teacher(teacher)
                .build();


        courseRepository.save(course);
    }

    @Test
    public void  findAllPagination(){

        Pageable firstPageWithThreeRecords =
                PageRequest.of(0,3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1,2);

        List<Course> coursesAvailable = courseRepository.findAll(secondPageWithTwoRecords).getContent();
        long totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();
        long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();

        System.out.println("Courses : " + coursesAvailable);
        System.out.println("total no.of pages : " + totalPages);
        System.out.println("total no.of elements : " + totalElements);


    }


    @Test
    public void  findAllSorting(){

        Pageable sortByTitle =
                PageRequest.of(0,2, Sort.by("title"));

        Pageable sortByCreditDesc =
                PageRequest.of(0,2, Sort.by("credits").descending());

        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(0,2, Sort.by("title").descending().and(Sort.by("credits").descending()));

        List<Course> coursesAvailable = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();
        long totalPages = courseRepository.findAll(sortByTitleAndCreditDesc).getTotalPages();
        long totalElements = courseRepository.findAll(sortByTitleAndCreditDesc).getTotalElements();

        System.out.println("Courses : " + coursesAvailable);
        System.out.println("total no.of pages : " + totalPages);
        System.out.println("total no.of elements : " + totalElements);


    }

    @Test
    public void printFindByTitleContaining(){

        Pageable firstPageTenRecords = PageRequest.of(0,10);

        List<Course> courses =
                courseRepository.findByTitleContaining("D",firstPageTenRecords).getContent();

        System.out.println("Courses = " + courses);

        }

    @Test
    public void saveCourseWithStudentAndTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Singh")
                .emailId("abhishekjK@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .teacher(teacher)
                .credits(12)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }
}