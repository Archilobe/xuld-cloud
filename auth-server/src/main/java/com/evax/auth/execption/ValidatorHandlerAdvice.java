package com.evax.auth.execption;

import com.evax.common.response.ResultCode;
import com.evax.common.response.ResultJson;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidatorHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultJson bindExceptionHandler(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String errorMessage = "Invalid Request: ";
        for (FieldError fieldError : fieldErrors) {
            errorMessage += fieldError.getDefaultMessage() + ",";
        }
        return ResultJson.failure(ResultCode.BAD_REQUEST, errorMessage);
    }
}
