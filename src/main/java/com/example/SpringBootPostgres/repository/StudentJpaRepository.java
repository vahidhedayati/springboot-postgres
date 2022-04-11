package com.example.SpringBootPostgres.repository;

import com.example.SpringBootPostgres.model.SimpleStudent;
import com.example.SpringBootPostgres.model.Student;
import com.example.SpringBootPostgres.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * This JpaRepository is an implementation of spring-data-jpa
 *
 * You need a repository for every aggregate or aggregate root
 * you don't need one for every entity
 *
 * This is a cluster of entities that are represented as one like the user which has may addresses etc
 *
 *
 * Query Deviation are for very simple queries
 */
public interface StudentJpaRepository extends JpaRepository<Student, Integer> {
    int countByStatus(int status);

    //This a dynamic query deviation
    Optional<Student> findOneByName(String name);

    //This a dynamic query deviation
    Stream<Student> findAllByName(String name);

    @Async
    CompletableFuture<Student> findOneByStatus(Integer status);


    /**
     * Annotated queries
     */
    @Query("SELECT u FROM Student u WHERE u.status = 1")
    Collection<Student> findAllActiveStudents();

    @Query(value = "SELECT * FROM USERS u WHERE u.status = 1", nativeQuery = true)
    Collection<Student> findAllActiveStudentsNative();

    @Query("SELECT u FROM Student u WHERE u.status = ?1")
    Student findStudentByStatus(Integer status);

    @Query(value = "SELECT * FROM Students u WHERE u.status = ?1", nativeQuery = true)
    Student findStudentByStatusNative(Integer status);

    @Query("SELECT u FROM Student u WHERE u.status = ?1 and u.name = ?2")
    Student findStudentByStatusAndName(Integer status, String name);

    @Query("SELECT u FROM Student u WHERE u.status = :status and u.name = :name")
    Student findStudentByStatusAndNameNamedParams(@Param("status") Integer status, @Param("name") String name);

    @Query(value = "SELECT * FROM Students u WHERE u.status = :status AND u.name = :name", nativeQuery = true)
    Student findStudentByStatusAndNameNamedParamsNative(@Param("status") Integer status, @Param("name") String name);

    @Query("SELECT u FROM Student u WHERE u.status = :status and u.name = :name")
    Student findStudentByStudentStatusAndStudentName(@Param("status") Integer userStatus, @Param("name") String userName);

    @Query("SELECT u FROM Student u WHERE u.name like ?1%")
    Student findStudentByNameLike(String name);

    @Query("SELECT u FROM Student u WHERE u.name like :name%")
    Student findStudentByNameLikeNamedParam(@Param("name") String name);

    @Query(value = "SELECT * FROM users u WHERE u.name LIKE ?1%", nativeQuery = true)
    Student findStudentByNameLikeNative(String name);

    @Query(value = "SELECT u FROM Student u")
    List<Student> findAllStudents(Sort sort);

    @Query(value = "SELECT u FROM Student u ORDER BY id")
    Page<Student> findAllStudentsWithPagination(Pageable pageable);

    @Query(value = "SELECT * FROM Students ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM Students", nativeQuery = true)
    Page<Student> findAllStudentsWithPaginationNative(Pageable pageable);
    //Slice<Student> findAllStudentsWithPaginationNative(Pageable pageable);

    List<SimpleStudent> findSimplyByName(String name);

    @Modifying
    @Query("update Student u set u.status = :status where u.name = :name")
    int updateStudentSetStatusForName(@Param("status") Integer status, @Param("name") String name);

    @Modifying
    @Query(value = "UPDATE Students u SET u.status = ? WHERE u.name = ?", nativeQuery = true)
    int updateStudentSetStatusForNameNative(Integer status, String name);
}
