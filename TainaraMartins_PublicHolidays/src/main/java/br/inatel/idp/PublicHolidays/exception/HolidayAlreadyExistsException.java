package br.inatel.idp.PublicHolidays.exception;

import br.inatel.idp.PublicHolidays.model.entity.Holiday;

public class HolidayAlreadyExistsException extends RuntimeException {
    public HolidayAlreadyExistsException(Holiday holiday) {
        super(String.format("There is already a holiday on this date "
                + holiday.getDate() +" in the city of " + holiday.getCityName()));
    }
}
