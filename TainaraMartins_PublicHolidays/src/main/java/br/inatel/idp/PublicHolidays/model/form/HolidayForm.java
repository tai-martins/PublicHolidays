package br.inatel.idp.PublicHolidays.model.form;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayForm {
    private String year;
    private String country;

    private LocalDate date;
}
