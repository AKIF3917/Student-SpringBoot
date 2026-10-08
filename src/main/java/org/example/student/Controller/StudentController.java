package org.example.student.Controller;

import jakarta.validation.Valid;
import org.example.student.Model.Student;
import org.example.student.Service.StudentService;
import org.example.student.dto.CreatStudentReqDto;
import org.example.student.dto.CreatStudentRespDto;
import org.example.student.dto.UpdateStudentReqDto;
import org.example.student.dto.UpdateStudentRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity <CreatStudentRespDto> createStudent(@Valid @RequestBody CreatStudentReqDto studentReqDto){

      CreatStudentRespDto resp= studentService.createStudent(studentReqDto);
       return ResponseEntity.ok(resp);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CreatStudentRespDto> getStudent(@PathVariable Long id){
              CreatStudentRespDto Sr= studentService.getStudent(id);
               return ResponseEntity.ok(Sr);
    }
    @GetMapping
    public ResponseEntity <List<CreatStudentRespDto>> getAllStudent(){
        List<CreatStudentRespDto> Sl= studentService.getAllStudent();
        return ResponseEntity.ok(Sl);
    }
    @PutMapping
    public ResponseEntity<UpdateStudentRespDto> putStudent(@PathVariable Long id,
                                                           @RequestBody UpdateStudentReqDto studentReq){
        UpdateStudentRespDto us=studentService.putStudent(id,studentReq);
        if(us==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(us);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <Student> delStudent(@PathVariable Long id){
        studentService.delStudent(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    public ResponseEntity <List<Student>> delStudentAll(){
        studentService.delAllStudent();
       return ResponseEntity.noContent().build();
    }
    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity <String> softDelete(@PathVariable Long id){
        studentService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
