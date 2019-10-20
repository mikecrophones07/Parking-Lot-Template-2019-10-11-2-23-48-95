package com.thoughtworks.parking_lot.Controller;

import com.thoughtworks.parking_lot.Dto.StatusResponse;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StatusResponse notFoundException(NotFoundException ex){
        StatusResponse errorResponse = new StatusResponse();
        errorResponse.setStatusCode(404);
        errorResponse.setStatusMsg(ex.getMessage());
        return errorResponse;
    }
}
