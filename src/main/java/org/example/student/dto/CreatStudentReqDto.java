package org.example.student.dto;

import jakarta.validation.constraints.*;

public class CreatStudentReqDto {
    @NotBlank(message="Name can't be empty")
    @Size(min=2,max=50)
    private String Name;
    @NotNull
    @Min(value=18,message="age should be greater than 18")
    private int age;
    @NotBlank
    @Email(message="mail should be valid")
    private String mail;
    @NotNull
    private String Subject;

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
