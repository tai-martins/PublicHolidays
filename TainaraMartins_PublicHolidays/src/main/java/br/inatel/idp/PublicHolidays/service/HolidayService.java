package br.inatel.idp.PublicHolidays.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.inatel.idp.PublicHolidays.adapter.HolidayAdapter;
import br.inatel.idp.PublicHolidays.mapper.HolidayMapper;
import br.inatel.idp.PublicHolidays.model.dto.HolidayDto;
import br.inatel.idp.PublicHolidays.model.entity.Holiday;
import br.inatel.idp.PublicHolidays.repository.HolidayRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HolidayService {

	@Autowired
	private HolidayRepository holidayRepository;
	@Autowired
	private HolidayAdapter holidayAdapter;

	public List<HolidayDto> findHoliday() {
		List<Holiday> holidayList = holidayRepository.findAll();
		return HolidayMapper.toHolidayDtoLits(holidayList);
	}
	
	public List<HolidayDto> findHolidayByLocal(String cityName) {
		return HolidayMapper.toHolidayDtoLits(holidayRepository.findByLocal(cityName));
	}
	
	public List<HolidayDto> findHolidayByDate(LocalDate date) {
		return HolidayMapper.toHolidayDtoLits(holidayRepository.findByDate(date));
	}
	
//	public HolidayDto saveHoliday(HolidayDto holidayDto) {
//        Holiday holiday = HolidayMapper.toHoliday(holidayDto);
//
//        if(holidayNotExists(holiday)){
//            return HolidayMapper.toHolidayDto(holidayRepository.save(holiday));
//        }
//	}
	
//	private Boolean holidayNotExists(Holiday holiday){
//
//        return holidayAdapter.getAllRecipe(recipeEvaluation.getRecipeId())
//                .stream()
//                .anyMatch(results -> results.getResults()[0].getDisplay()
//                .equals(recipeEvaluation.getRecipeId()));
//	
//	
}
