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

	private LocalDate date;
	private String localName;
	private String name;
	private String contryCode;
	private boolean fixed;
	private boolean global;
	private ArrayList<String> counties;
	private ArrayList<String> launchYear;
	private String type;

	public LocalDate getDate() {
		return date;
	}
}
