package br.inatel.idp.PublicHolidays.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.inatel.idp.PublicHolidays.model.entity.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.inatel.idp.PublicHolidays.model.dto.HolidayDto;
import br.inatel.idp.PublicHolidays.service.HolidayService;

/**
 * Controller class is responsible to the REST requests
 * @author tainara.martins
 */
@RestController
@RequestMapping("/holiday")
public class HolidayController {
	
	@Autowired
	private HolidayService holidayService;

	/**
	 * Method responsible to list all holidays registered at the database
	 * @param id
	 * @return list holidays
	 */
	@GetMapping
	public ResponseEntity<List<HolidayDto>> holidaysAll(@RequestParam(required = false) Optional<Integer>id){
		return ResponseEntity.ok().body(holidayService.findHoliday());
	}

	/**
	 * Method responsible to list holidays by city
	 * @param cityName
	 * @return list holidays by local
	 */
	@GetMapping("/local")
	public ResponseEntity<List<HolidayDto>> localHolidays(@RequestParam("cityName") @PathVariable String cityName){
		return ResponseEntity.ok().body(holidayService.findHolidayByLocal(cityName));
	}

	/**
	 * Method responsible to list all holidays by date
	 * @param date
	 * @return list holidays by date
	 */
	@GetMapping("/date")
	public ResponseEntity<List<Holiday>> dateHolidays(@RequestParam("date") @PathVariable String date){
		return ResponseEntity.ok().body(holidayService.findHolidayByDate(date));
	}

	/**
	 * Method responsible to resgister a new drag at the race from a POST reques
	 * @param holidayDto
	 * @return holidayDto with the information that were registered
	 */
	@PostMapping
    public ResponseEntity<?> addHoliday(@RequestBody @Valid HolidayDto holidayDto) throws Exception {
        return ResponseEntity.created(null).body(holidayService.saveHoliday(holidayDto));
    }
	
}
