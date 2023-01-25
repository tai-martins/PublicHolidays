package br.inatel.idp.PublicHolidays.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import br.inatel.idp.PublicHolidays.exception.HolidayAlreadyExistsException;
import br.inatel.idp.PublicHolidays.exception.HolidayNotFoundException;
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

    public List<Holiday> findHolidayByDate(String date) {
        List<Holiday> list = holidayRepository.findByDate(date);
        return list;
    }

    public HolidayDto saveHoliday(@Valid HolidayDto holidayDto) throws Exception {
        Holiday holiday = HolidayMapper.toHoliday(holidayDto);

        if (exist(holiday)) {
            throw new HolidayNotFoundException(holiday);
        } else if(holidayRepository.findByDate(holiday.getDate())!=null &
                holidayRepository.findByCityName(holiday.getCityName())!=null){
            throw new HolidayAlreadyExistsException(holiday);
        }
        else {
            return HolidayMapper.toHolidayDto(holidayRepository.save(holiday));
        }
    }

    public boolean exist(Holiday holiday) {
        String year = holiday.getYear();
        String country = holiday.getCountryCode();
        return holidayAdapter.getPublicHoliday(year, country).stream()
                .anyMatch(h -> h.getDate().equals(holiday.getDate()));
    }

//	public List<PublicHoliday> teste(HolidayForm hForm){
//		String year = hForm.getYear();
//		String country = hForm.getCountry();
//		return holidayAdapter.getPublicHoliday(year, country);
//	}
}
