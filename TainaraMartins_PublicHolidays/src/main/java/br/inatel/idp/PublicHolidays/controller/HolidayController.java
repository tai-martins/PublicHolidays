package br.inatel.idp.PublicHolidays.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.idp.PublicHolidays.model.dto.HolidayDto;
import br.inatel.idp.PublicHolidays.model.entity.Holiday;
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
	
	@GetMapping("/{local}")
	public ResponseEntity<List<HolidayDto>> localHolidays(@RequestParam(required = false) String cityName){
		return ResponseEntity.ok().body(holidayService.findHolidayByLocal(cityName));
	}
	
	@GetMapping("/{date}")
	public ResponseEntity<List<HolidayDto>> dateHolidays(@RequestParam(required = false) LocalDate date){
		return ResponseEntity.ok().body(holidayService.findHolidayByDate(date));
	}
	
//	@PostMapping
//    public ResponseEntity<HolidayDto> addHoliday(@RequestBody @Valid HolidayDto holidayDto) {
//        return ResponseEntity.created(null).body(holidayService.saveHoliday(holidayDto));
//    }
	
}
