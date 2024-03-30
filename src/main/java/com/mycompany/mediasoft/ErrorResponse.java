package com.mycompany.mediasoft;

import lombok.Data;

/**
 * Класс, описывающий структуру ответа об ошибке.
 */
@Data
public class ErrorResponse {
    
    /**
     * Сообщение об ошибке.
     */
    private String message;
    
    /**
     * HTTP статус ошибки.
     */
    private int status;
    
    /**
     * Конструктор класса.
     * @param message Сообщение об ошибке.
     * @param status HTTP статус ошибки.
     */
    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
