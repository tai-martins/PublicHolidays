package br.inatel.idp.PublicHolidays.model.rest;

import java.time.LocalDate;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicHoliday {

	private String date;
	private String localName;
	private String name;
	private String countryCode;
	private String type;

	public String getDate() {
		return date;
	}
}
