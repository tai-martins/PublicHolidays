package br.inatel.idp.PublicHolidays.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import br.inatel.idp.PublicHolidays.exception.HolidayNotFoundException;
import br.inatel.idp.PublicHolidays.model.form.HolidayForm;
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

    public List<HolidayDto> findHolidayByDate(LocalDate date) {
        return HolidayMapper.toHolidayDtoLits(holidayRepository.findByDate(date));
    }

    public List<HolidayDto> saveHoliday(@Valid HolidayForm holidayForm) throws Exception {
        List<PublicHoliday> pHoliday = holidayAdapter
                .getPublicHoliday(holidayForm.getYear(), holidayForm.getCountryCode());
        List<Holiday> list = holidayRepository.findByDate(holidayForm.getDate());
        if (exist(holidayForm)) {
            throw new HolidayNotFoundException(holidayForm);
        } else {
            Holiday holiday = Holiday.builder()
                    .id(holidayForm.getId())
                    .year(holidayForm.getYear())
                    .contryCode(holidayForm.getCountryCode())
                    .date(holidayForm.getDate())
                    .cityName(holidayForm.getCity())
                    .holidayName(holidayForm.getHolidayName())
                    .build();
//
//            holidayRepository.save(holiday);
//            return HolidayMapper.toHolidayDto(holiday);
            return HolidayMapper.toHolidayDto(holidayRepository.save(holiday));
        }
    }

    public boolean exist(HolidayForm holidayForm) {
        String year = holidayForm.getYear();
        String country = holidayForm.getCountryCode();
        return holidayAdapter.getPublicHoliday(year, country).stream()
                .anyMatch(h -> h.getDate().equals(holidayForm.getDate()));
    }

//	public List<PublicHoliday> teste(HolidayForm hForm){
//		String year = hForm.getYear();
//		String country = hForm.getCountry();
//		return holidayAdapter.getPublicHoliday(year, country);
//	}
}
