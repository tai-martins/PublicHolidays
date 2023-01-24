package br.inatel.idp.PublicHolidays.model.form;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
public class HolidayForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String year;
    private String countryCode;

    private String city;
    private LocalDate date;

    private String holidayName;
}
