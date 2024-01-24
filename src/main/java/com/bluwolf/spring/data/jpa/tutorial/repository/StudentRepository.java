package com.bluwolf.spring.data.jpa.tutorial.repository;

import com.bluwolf.spring.data.jpa.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //JPA Queries based on documentation provided by Spring Data JPA NB: Naming convention very important.
    List<Student> findByFirstName(String firstName);

    List<Student>findByFirstNameContaining(String name);

    List<Student>findByLastNameNotNull();

    List<Student>findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    //JPQL - Note that JPQL Queries are based on the classes you create and not the tables present in the database
    @Query("select s from Student s where s.emailId = ?1 ")
    Student getStudentByEmailAddress(String emailId);

    @Query("select s.firstName from Student s where s.emailId = ?1 ")
    String getStudentFirstNameByEmailAddress(String emailId);


    //Native SQL - Based on the table data not class
    @Query(
            value="select * from tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);


    //Native named params to prevent ?1 ?2 etc.
    @Query(
            value="select * from tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId")String emailId);

    //All repository methods above are data fetching.Let us try some data modifying (create/update) methods below
    //Since we are making a change (transaction) in the db we use @Transactional
    /**Ideally @Transactional will be used in a serviceImpl (added on top of service) where you are calling a method having multiple transactions
     *  (say updating 3 different records in same/different tables) then all the changes will be made and if all changes are successful
     *  then only the changes will be pushed to db (committed transaction)- making it safe.
     *  If any one of the changes fail the transaction will be dropped and db remains valid.( transaction rolled back)
     *  Evn applicable if a service layer is calling multiple different repositories we can make all changes to single transaction
    */

    @Modifying
    @Transactional
    @Query(
            value=" update tbl_student set first_name = :firstName where email_address = :emailId" ,
            nativeQuery = true
    )
    int updateStudentNameByEmailId(@Param("firstName")String firstName, @Param("emailId")String emailId);



}
