package com.javaweb.springbootnonjwt.controlleradvice;

import com.javaweb.springbootnonjwt.DTO.ErrorResponseDTO;
import com.javaweb.springbootnonjwt.customexception.FiledRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Object> handleArithmeticException(
            ArithmeticException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setError(ex.getMessage());
        List<String> detail = new ArrayList<String>();
        detail.add("Lam sao so nguyen lai chia cho 0 duoc");
        errorResponseDTO.setDetail(detail);

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(FiledRequiredException.class)
    public ResponseEntity<Object> handleFiledRequireException(
            FiledRequiredException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setError(ex.getMessage());
        List<String> detail = new ArrayList<String>();
        detail.add("Ten toa nha hoac dia chi chua duoc gui ve");
        errorResponseDTO.setDetail(detail);

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
