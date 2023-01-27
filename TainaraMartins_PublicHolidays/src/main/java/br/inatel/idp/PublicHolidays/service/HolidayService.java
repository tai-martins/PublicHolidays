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

/**
 * Service class responsable to do the logic for de endpoints
 * @author tainara.martins
 */
@Service
@Slf4j
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;
    @Autowired
    private HolidayAdapter holidayAdapter;

    /**
     * Method responsible to list all holidays registered at the database
     * @return holiday list
     */
    public List<HolidayDto> findHoliday() {
        List<Holiday> holidayList = holidayRepository.findAll();
        return HolidayMapper.toHolidayDtoLits(holidayList);
    }

    /**
     * Method responsible to list holidays by local registered at the database
     * @param cityName
     * @return list holidays by local
     */
    public List<HolidayDto> findHolidayByLocal(String cityName) {
        return HolidayMapper.toHolidayDtoLits(holidayRepository.findByCityName(cityName));
    }

    /**
     * Method responsible to list holidays by date registered at the database
     * @param date
     * @return list holidays by date
     */
    public List<Holiday> findHolidayByDate(String date) {
        List<Holiday> list = holidayRepository.findByDate(date);
        return list;
    }

    /**
     * Method reponsible to register a new drag at the database
     * @param holidayDto
     * @return Holidat Dto
     * @throws Exception
     */
    public HolidayDto saveHoliday(@Valid HolidayDto holidayDto) throws Exception {
        Holiday holiday = HolidayMapper.toHoliday(holidayDto);
        List<Holiday> verifyDate = holidayRepository.findByDate(holidayDto.getDate());
        List<Holiday> verifyLocal = holidayRepository.findByCityName(holidayDto.getCityName());

        if (exist(holiday)) {
            throw new HolidayNotFoundException(holiday);
        }
        else if((verifyDate.size() != 0) && (verifyLocal.size() != 0)){
            throw new HolidayAlreadyExistsException(holiday);
        }
        else {
            return HolidayMapper.toHolidayDto(holidayRepository.save(holiday));
        }
    }

    /**
     * Method that verif exist holiday in external api
     * @param holiday
     * @return true or false to holiday
     */
    public boolean exist(Holiday holiday) {
        String year = holiday.getYear();
        String country = holiday.getCountryCode();
        return holidayAdapter.getPublicHoliday(year, country).stream()
                .anyMatch(h -> h.getDate().equals(holiday.getDate()));
    }
}
