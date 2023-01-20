package br.inatel.idp.PublicHolidays.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Holiday {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@NotNull
	private String cityName;
	@NotNull
	private String holidayName;
	@NotNull
	@JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate date;

	private String contryCode;
	private int year;

	public LocalDate getDate() {
		return date;
	}
}
