package wd.team4.everycare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionApiController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException(MethodArgumentNotValidException e) {
        System.out.println(e.getBindingResult().getAllErrors());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        MyResponse<Object> myResponse = MyResponse.builder()
                .header(StatusEnum.BAD_REQUEST)
                .message("유효성 검사 실패")
                .body(errors).build();
        return new ResponseEntity(myResponse, HttpStatus.BAD_REQUEST);
    }
}
