package br.inatel.idp.PublicHolidays.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.idp.PublicHolidays.model.dto.HolidayDto;
import br.inatel.idp.PublicHolidays.model.entity.Holiday;

public class HolidayMapper {

	public static Holiday toHoliday(HolidayDto holidayDto) {
		Holiday holiday = Holiday.builder()
				.id(holidayDto.getId())
				.year(holidayDto.getYear())
				.contryCode(holidayDto.getContryCode())
				.cityName(holidayDto.getCityName())
				.holidayName(holidayDto.getHolidayName())
				.date(holidayDto.getDate())
				.build();



		return holiday;
	
	}
	
	public static HolidayDto toHolidayDto(Holiday holiday) {
		HolidayDto holidayDto = HolidayDto.builder()
				.id(holiday.getId())
				.contryCode(holiday.getContryCode())
				.year(holiday.getYear())
				.cityName(holiday.getCityName())
				.holidayName(holiday.getHolidayName())
				.date(holiday.getDate())
				.build();
		
		return holidayDto;
	}

	public static List<HolidayDto> toHolidayDtoLits(List<Holiday> optional){
		return optional.stream().map(h -> toHolidayDto(h)).collect(Collectors.toList());
	}

}
