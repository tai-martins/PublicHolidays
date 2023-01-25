package br.inatel.idp.PublicHolidays.handler;

import br.inatel.idp.PublicHolidays.exception.ConnectionException;
import br.inatel.idp.PublicHolidays.exception.HolidayAlreadyExistsException;
import br.inatel.idp.PublicHolidays.exception.HolidayNotFoundException;
import br.inatel.idp.PublicHolidays.model.rest.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(HolidayNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error holidayNotFoundException(HolidayNotFoundException holidayNotFoundException){
        return Error.builder().httpStatusCode(HttpStatus.NOT_FOUND).message(holidayNotFoundException.getMessage()).build();
    }
    @ExceptionHandler(HolidayAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error holidayAlreadyExistsException(HolidayAlreadyExistsException holidayAlreadyExistsException){
        return Error.builder().httpStatusCode(HttpStatus.NOT_FOUND)
                .message(holidayAlreadyExistsException.getMessage()).build();
    }

    @ExceptionHandler(ConnectionException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public Error connectionException(ConnectionException connectionException){

        return Error.builder()
                .httpStatusCode(HttpStatus.SERVICE_UNAVAILABLE)
                .message(connectionException.getMessage())
                .build();
    }

}
