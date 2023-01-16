package br.inatel.idp.PublicHolidays.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HolidayDto {
	
	private int id;
	private String cityName;
	private LocalDate date;

}
