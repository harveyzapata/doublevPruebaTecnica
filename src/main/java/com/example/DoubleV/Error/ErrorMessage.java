package com.example.DoubleV.Error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorMessage {
    int code;
    String message;

    public static ErrorMessage format(ErrorMessage errorMessage, String format) {
        errorMessage.setMessage(String.format(errorMessage.getMessage(), format));
        return errorMessage;
    }


    public static ErrorMessage DEFAULT_ERROR = new ErrorMessage(-1, "Unknown error");
    public static ErrorMessage TICKET_NOT_FOUND = new ErrorMessage(404, "Ticket no encontrado");
}
