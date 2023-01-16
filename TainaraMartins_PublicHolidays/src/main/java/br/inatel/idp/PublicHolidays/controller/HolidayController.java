package br.inatel.idp.PublicHolidays.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.idp.PublicHolidays.model.dto.HolidayDto;
import br.inatel.idp.PublicHolidays.service.HolidayService;

@RestController
@RequestMapping("/holiday")
public class HolidayController {
	
	@Autowired
	private HolidayService holidayService;
	
	@GetMapping
	public ResponseEntity<List<HolidayDto>> holidaysAll(@RequestParam(required = false) Optional<Integer>id){
		return ResponseEntity.ok().body(holidayService.findHoliday());
	}
	
}
