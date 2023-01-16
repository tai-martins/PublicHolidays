package br.inatel.idp.PublicHolidays.service;

import java.util.List;

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
	
	public Holiday saveHoliday(HolidayDto holidayDto) {
		return HolidayMapper.toHoliday(holidayDto);
	}
	
//	public List<HolidayDto> getHolidayByLocal(String cityName){
//		return HolidayMapper.toHolidayDto(null)
//	}
	
}
