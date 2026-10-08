package org.example.student.Service;

import org.example.student.Model.Student;
import org.example.student.Reposatory.StudentRepository;
import org.example.student.dto.CreatStudentReqDto;
import org.example.student.dto.CreatStudentRespDto;
import org.example.student.dto.UpdateStudentReqDto;
import org.example.student.dto.UpdateStudentRespDto;
import org.example.student.exception.DuplicateResourseException;
import org.example.student.exception.ResoureNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
  private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreatStudentRespDto createStudent(CreatStudentReqDto studentreqDto){
       Student student=mapToEntity(studentreqDto);
        if (emailExist(student)) {
            throw new DuplicateResourseException("Student with id " + student.getMail() + " already Exist:");
        }
       Student studentResp=studentRepository.save(student);
       return mapToDto(studentResp);
    }
    public CreatStudentRespDto getStudent(Long id){
         Student findStudent=studentRepository
                 .findByIdAndDeletedFalse(id)
                 .orElseThrow(() -> new ResoureNotFoundException("student with id " + id + " not found"));
        return mapToDto(findStudent);
    }
    public List<CreatStudentRespDto> getAllStudent(){
        List<Student> sl=studentRepository.findByDeletedFalse();
        return sl.stream()
                .map(this::mapToDto)
                .toList();
    }
    public UpdateStudentRespDto putStudent(Long id, UpdateStudentReqDto studentReq){
     Student ExistingStudent=studentRepository
             .findByIdAndDeletedFalse(id)
             .orElseThrow(()-> new RuntimeException("Student with id " + id + " not found"));
       ExistingStudent.setName(studentReq.getName());
       ExistingStudent.setAge(studentReq.getAge());
       ExistingStudent.setSubject(studentReq.getSubject());
        Student savedStudent= studentRepository.save(ExistingStudent);
        return mapToUpdateDto(savedStudent);
    }
    public void delStudent(Long id){
        Student student =studentRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Student with id "+id+" not Found"));
        studentRepository.delete(student);

    }
    public void delAllStudent(){
        List<Student> fs=studentRepository.findByDeletedFalse();
        if(fs.isEmpty()){
            throw  new DuplicateResourseException("Student not found:");
        }
        studentRepository.deleteAll(fs);
    }
    public void softDelete(Long id){
       Student StudentISdeleted=studentRepository
               .findByIdAndDeletedFalse(id)
               .orElseThrow(()-> new RuntimeException("Student with id "+id+" not Found"));
         StudentISdeleted.setDeleted(true);
         studentRepository.save(StudentISdeleted);
    }
    private Student mapToEntity(CreatStudentReqDto studentReqDto){
        Student student=new Student();
        student.setName(studentReqDto.getName());
        student.setAge(studentReqDto.getAge());
        student.setMail(studentReqDto.getMail());
        student.setSubject(studentReqDto.getSubject());
        student.setDeleted(false);
        return student;
    }
    private CreatStudentRespDto mapToDto(Student student){
        CreatStudentRespDto respDto=new CreatStudentRespDto();
        respDto.setId(student.getId());
        respDto.setName(student.getName());
        respDto.setAge(student.getAge());
        respDto.setMail(student.getMail());
        respDto.setSubject(student.getSubject());
        respDto.setCreatedAt(student.getCreatedAt());
        respDto.setUpdatedAt(student.getUpdatedAt());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        return respDto;
    }
    private UpdateStudentRespDto mapToUpdateDto(Student student){
        UpdateStudentRespDto respDto=new UpdateStudentRespDto();
        respDto.setId(student.getId());
        respDto.setName(student.getName());
        respDto.setAge(student.getAge());
        respDto.setMail(student.getMail());
        respDto.setSubject(student.getSubject());
        respDto.setUpdatedAt(student.getUpdatedAt());
        return respDto;
    }
    private boolean emailExist(Student student){
        return studentRepository.existsByMail(student.getMail());
    }
}
