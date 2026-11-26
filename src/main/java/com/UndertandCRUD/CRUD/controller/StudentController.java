package com.UndertandCRUD.CRUD.controller;

import com.UndertandCRUD.CRUD.dto.CreatStudentRequestDTO;
import com.UndertandCRUD.CRUD.dto.CreateStudentResponseDTO;
import com.UndertandCRUD.CRUD.dto.UpdateStudentRequestDto;
import com.UndertandCRUD.CRUD.dto.UpdateStudentResponseDto;
import com.UndertandCRUD.CRUD.entity.Student;
import com.UndertandCRUD.CRUD.service.StudentService;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // validations -->spring-boot-starter validation

    @PostMapping
    public ResponseEntity<CreateStudentResponseDTO> createStudent(
           @Valid @RequestBody CreatStudentRequestDTO studentRequestDTO) {

        CreateStudentResponseDTO createdStudent = studentService.createStudent(studentRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getstudent(@PathVariable Long id) {
        CreateStudentResponseDTO studentres = studentService.getstudent(id);

        return ResponseEntity.ok(studentres);
    }

    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> getallstudent() {
        List<CreateStudentResponseDTO> studentall = studentService.getAllstudents();
        if (studentall.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentall);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequestDto studentreq) {
        UpdateStudentResponseDto studentres = studentService.updatestudent(id, studentreq);
        if (studentres == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentres);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        studentService.deletestudent(id);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/delete_soft/{id}")
    public ResponseEntity<String> deleteStudentSoft(@PathVariable Long id) {
        studentService.softDeleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}

