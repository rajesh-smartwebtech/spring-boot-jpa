package com.webapp.app.database;

import com.webapp.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student,Long> {


    Student findByFirstName(String name);

    Student findById(int id);

    List<Student> findAll();

    Student save(Student student);

    @Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Student e WHERE e.id = :studentId")       // it will delete all the record with specific name
    int deleteById(@Param("studentId") int studentId);

    @Modifying      // to mark delete or update query
    @Query(value = "UPDATE Student  SET firstName = :studentName , contact = :studentContact WHERE id = :studentId")       // it will delete all the record with specific name
    int updateStudentInfo(@Param("studentId") int studentId, @Param("studentName") String studentName,@Param("studentContact") String studentContact);
}