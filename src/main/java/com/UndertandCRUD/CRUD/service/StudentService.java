package com.UndertandCRUD.CRUD.service;

import com.UndertandCRUD.CRUD.dto.CreatStudentRequestDTO;
import com.UndertandCRUD.CRUD.dto.CreateStudentResponseDTO;
import com.UndertandCRUD.CRUD.dto.UpdateStudentRequestDto;
import com.UndertandCRUD.CRUD.dto.UpdateStudentResponseDto;
import com.UndertandCRUD.CRUD.entity.Student;
import com.UndertandCRUD.CRUD.exception.DublicateResourceException;
import com.UndertandCRUD.CRUD.exception.ResourceNotfound;
import com.UndertandCRUD.CRUD.repository.Studentrepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final Studentrepository studentrepository;

    public StudentService(Studentrepository studentrepository) {
        this.studentrepository = studentrepository;
    }

    public CreateStudentResponseDTO createStudent(CreatStudentRequestDTO studentRequestDTO) {

        Student student = mapToEntity(studentRequestDTO);

        if(emailExist(student)){
            throw new DublicateResourceException("Student already exists with email " + student.getEmail() + " ALL READY exists");
        }

        Student savedStudent = studentrepository.save(student);

        return mapToDto(savedStudent);
    }

    public CreateStudentResponseDTO getstudent(Long id) {

//        Student student = studentrepository
//                .findById(id)
//                .orElse(null);
//        return mapToDto(student);
        Student student = studentrepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotfound("Student Not Found with id: " + id));
        return mapToDto(student);
    }

    public List<CreateStudentResponseDTO> getAllstudents() {

        List<Student> studentList = studentrepository.findByIsdeletedFalse();

        return studentList.stream()
                .map(this::mapToDto)
                .toList();
    }

    public UpdateStudentResponseDto updatestudent(Long id, UpdateStudentRequestDto studentReq) {

        Student existstudentres = studentrepository
                .findByIdAndIsdeletedFalse(id)
                .orElseThrow(()-> new ResourceNotfound("Student Not Found with id: " + id));

            existstudentres.setName(studentReq.getName());
            existstudentres.setAge(studentReq.getAge());
            existstudentres.setSubject(studentReq.getSubject());
            existstudentres.setRollNo(studentReq.getRollNo());
            existstudentres.setactive(false);
            existstudentres.setUpdatedAt(LocalDateTime.now());
            Student savestudent = studentrepository.save(existstudentres);

            return mapToUpdatedDto(savestudent);

    }

    public void deletestudent(Long id) {
        Student student = studentrepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotfound("Student Not Found with id: " + id));
                studentrepository.delete(student);
    }

    public void softDeleteStudent(Long id) {
        Student studenttodeleted = studentrepository
                .findByIdAndIsdeletedFalse(id)
                .orElseThrow(()->
                        new ResourceNotfound("Student Not Found with id: " + id));
        studenttodeleted.setIsdeleted(true);
        studentrepository.save(studenttodeleted);
    }

    private Student mapToEntity(CreatStudentRequestDTO studentrequestDTO) {
        Student student = new Student();
        student.setName(studentrequestDTO.getName());
        student.setAge(studentrequestDTO.getAge());
        student.setSubject(studentrequestDTO.getSubject());
        student.setEmail(studentrequestDTO.getEmail());
        student.setRollNo(studentrequestDTO.getRollNo());
        // we use buider pattern
        student.setactive(false);
        student.setIsdeleted(false);
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        return student;
    }
    private CreateStudentResponseDTO mapToDto(Student student) {
        CreateStudentResponseDTO responsedto = new CreateStudentResponseDTO();
        responsedto.setName(student.getName());
        responsedto.setAge(student.getAge());
        responsedto.setSubject(student.getSubject());
        responsedto.setEmail(student.getEmail());
        responsedto.setRollNo(student.getRollNo());
        responsedto.setCreatedAt(student.getCreatedAt());
        responsedto.setUpdatedAt(student.getUpdatedAt());
        responsedto.setMessage("student successfully saved ");

        return responsedto;
    }
    private UpdateStudentResponseDto mapToUpdatedDto(Student student){
        UpdateStudentResponseDto responsedto = new UpdateStudentResponseDto();
        responsedto.setName(student.getName());
        responsedto.setAge(student.getAge());
        responsedto.setSubject(student.getSubject());
        responsedto.setEmail(student.getEmail());
        responsedto.setRollNo(student.getRollNo());
        responsedto.setUpdatedAt(student.getUpdatedAt());
        responsedto.setMessage("student successfully Updated");

        return responsedto;
    }
    private boolean emailExist(Student student) {
     return studentrepository.existsByEmail(student.getEmail());
    }
}


