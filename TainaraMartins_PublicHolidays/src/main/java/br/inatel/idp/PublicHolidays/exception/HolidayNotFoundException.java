package br.inatel.idp.PublicHolidays.exception;

import br.inatel.idp.PublicHolidays.model.entity.Holiday;

public class HolidayNotFoundException extends RuntimeException {
    public HolidayNotFoundException(Holiday holiday) {
        super(String.format("There is already a national holiday on this date ", holiday.getDate()));
    }
}
