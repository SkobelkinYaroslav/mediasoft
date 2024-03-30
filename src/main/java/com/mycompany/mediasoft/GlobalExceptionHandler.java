package com.mycompany.mediasoft;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;



/**
 * Класс, обрабатывающий исключения, которые могут возникнуть в приложении.
 */

public class GlobalExceptionHandler {
    
/**
     * Метод обрабатывает исключение типа Exception.
     * @param ex Исключение.
     * @return Ответ с информацией об ошибке.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse error = new ErrorResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}