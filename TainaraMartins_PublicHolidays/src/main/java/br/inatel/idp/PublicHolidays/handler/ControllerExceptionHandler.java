package br.inatel.idp.PublicHolidays.handler;

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

}
