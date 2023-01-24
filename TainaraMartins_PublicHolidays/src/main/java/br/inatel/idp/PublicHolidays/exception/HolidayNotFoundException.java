package br.inatel.idp.PublicHolidays.exception;

import br.inatel.idp.PublicHolidays.model.entity.Holiday;
import br.inatel.idp.PublicHolidays.model.form.HolidayForm;

public class HolidayNotFoundException extends RuntimeException {
    public HolidayNotFoundException(HolidayForm holidayForm) {
        super(String.format("There is already a national holiday on this date", holidayForm.getDate()));
    }
}
