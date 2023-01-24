package br.inatel.idp.PublicHolidays.model.rest;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Error {
	
	private HttpStatus httpStatusCode;
    private String message;

}
