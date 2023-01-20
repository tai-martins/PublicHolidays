package br.inatel.idp.PublicHolidays.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.inatel.idp.PublicHolidays.exception.HolidayNotFoundException;
import br.inatel.idp.PublicHolidays.model.rest.PublicHoliday;
import org.springframework.beans.factory.annotation.Autowired;
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
		return HolidayMapper.toHolidayDtoLits(holidayRepository.findByCityName(cityName));
	}

//	public HolidayDto findHolidayByDate(LocalDate date){
//		Optional<Holiday> opHoliday = holidayRepository.findByDate(date);
//
//	}
	
	public List<HolidayDto> findHolidayByDate(LocalDate date) {
		return HolidayMapper.toHolidayDtoLits(holidayRepository.findByDate(date));
	}

	public HolidayDto saveHoliday(@Valid HolidayDto holidayDto) {
		Holiday holiday = HolidayMapper.toHoliday(holidayDto);
		if(holidayRepository.findByDate(holidayDto.getDate()).isEmpty()) {
			return HolidayMapper.toHolidayDto(holidayRepository.save(holiday));
		}else if (holidayExist(holiday)) {
			return HolidayMapper.toHolidayDto(holidayRepository.save(holiday));
		}
		throw new HolidayNotFoundException(holiday);

//			return HolidayNotFoundException(holiday);

	}

	public boolean holidayExist(Holiday holiday) {
		List<PublicHoliday> holidayList = holidayAdapter.listAllPublicHolidays(holiday);
		return holidayList.stream().anyMatch(h -> h.getDate().equals(holiday.getDate()));
	}
}
