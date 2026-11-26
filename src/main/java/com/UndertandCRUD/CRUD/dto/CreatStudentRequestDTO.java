package com.UndertandCRUD.CRUD.dto;

import jakarta.validation.constraints.*;

public class CreatStudentRequestDTO {

    @NotBlank(message = "name can not be null")
    @Size(min = 2, max = 15 , message = "name in between 2 to 15")
    private String name;
    @Min(value = 18 , message = "age must greater then 18")
    private int age;

    @Email(message = "follow proper structure")
    private String email;
    @NotNull( message = "fill it correctly")
    private int rollNo;
    @Size(min = 2, max = 15 , message = "name in between 2 to 15")
    private String subject;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
