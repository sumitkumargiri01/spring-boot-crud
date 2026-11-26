package com.UndertandCRUD.CRUD.repository;

import com.UndertandCRUD.CRUD.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Studentrepository extends JpaRepository<Student, Long> {

    // Custom query methods to filter out records where soft-delete (isdeleted) is true
    Optional<Student> findByIdAndIsdeletedFalse(Long id);
    List<Student> findByIsdeletedFalse();

    Boolean existsByEmail(String email);

}

