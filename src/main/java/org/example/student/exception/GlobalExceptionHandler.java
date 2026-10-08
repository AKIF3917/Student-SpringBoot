package org.example.student.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.example.student.dto.CreatExceptionRespDto;
import org.example.student.dto.CreatValidationRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CreatValidationRespDto> MethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String,String> fieldErrors=new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error->fieldErrors.put(error.getField(),error.getDefaultMessage()));
     CreatValidationRespDto exceptionResp = new CreatValidationRespDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                fieldErrors
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResp);
    }
    @ExceptionHandler(DuplicateResourseException.class)
    public ResponseEntity<CreatExceptionRespDto> DuplicateResourseException(DuplicateResourseException ex, HttpServletRequest request){
        CreatExceptionRespDto exception=new CreatExceptionRespDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception);
    }
    @ExceptionHandler(ResoureNotFoundException.class)
    public ResponseEntity<CreatExceptionRespDto> ResourceNotFoundException(ResoureNotFoundException ex, HttpServletRequest request){
        CreatExceptionRespDto exception=new CreatExceptionRespDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception);
    }
    @ExceptionHandler(RuntimeException.class)
   public ResponseEntity<CreatExceptionRespDto> RunTimeExceptionHandler(RuntimeException ex, HttpServletRequest request){
        CreatExceptionRespDto exception=new CreatExceptionRespDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
       return ResponseEntity
               .status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body(exception);
   }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionRespDto> Exception(Exception ex,HttpServletRequest request){
//        ExceptionRespDto exception=new ExceptionRespDto(
//                LocalDateTime.now(),
//                HttpStatus.CONFLICT.value(),
//                HttpStatus.CONFLICT.getReasonPhrase(),
//                ex.getMessage(),
//                request.getRequestURI()
//        );
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(exception);
 //   }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CreatExceptionRespDto> GenericException(Exception ex, HttpServletRequest request){
        CreatExceptionRespDto exception=new CreatExceptionRespDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception);
    }
}
