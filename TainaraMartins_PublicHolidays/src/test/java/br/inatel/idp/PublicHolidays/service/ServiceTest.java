package br.inatel.idp.PublicHolidays.service;

import br.inatel.idp.PublicHolidays.adapter.HolidayAdapter;
import br.inatel.idp.PublicHolidays.model.dto.HolidayDto;
import br.inatel.idp.PublicHolidays.model.entity.Holiday;
import br.inatel.idp.PublicHolidays.model.rest.PublicHoliday;
import br.inatel.idp.PublicHolidays.repository.HolidayRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ServiceTest {

    @Mock
    private HolidayRepository holidayRepository;
    @Mock
    private HolidayAdapter holidayAdapter;
    @InjectMocks
    private HolidayService holidayService;

    private Holiday holiday;
    private HolidayDto holidayDto;
    List<Holiday> holidayList = new ArrayList<>();
    List<HolidayDto> holidayDtoList = new ArrayList<>();

    @Before
    public void init(){
        holiday = Holiday.builder()
                .id(1)
                .cityName("Santa Rita")
                .holidayName("Padroeira")
                .date("2023-05-24")
                .countryCode("BR")
                .year("2023")
                .build();

        holidayDto = HolidayDto.builder()
                .id(1)
                .cityName("Santa Rita")
                .holidayName("Padroeira")
                .date("2023-05-24")
                .countryCode("BR")
                .year("2023")
                .build();

        holidayList.add(holiday);
        holidayDtoList.add(holidayDto);
    }

    @Test
    public void givenListHolidaysByLocal_whenGetHolidaysByValidLocal_shouldReturnHolidayDtoList() {
        when(holidayRepository.findByCityName("Santa Rita")).thenReturn(Arrays.asList(holiday));
        List<HolidayDto> holidayDtoList = holidayService.findHolidayByLocal("Santa Rita");

        assertThat(holidayDtoList).isNotNull();
        assertEquals(holiday.getCityName(), "Santa Rita");
        assertThat(holidayDtoList.get(0).getCityName()).isEqualTo("Santa Rita");
    }
    @Test
    public void givenListHolidaysByInvalidLocal_whenGetHolidaysByInvalidLocal_shouldReturnHolidayDtoList() {
        when(holidayRepository.findByCityName("Pouso Alegre")).thenReturn(Arrays.asList(holiday));
        List<HolidayDto> holidayDtoList = holidayService.findHolidayByLocal("Santa Rita");

        assertThat(holidayDtoList.size()).isEqualTo(0L);
    }

    @Test
    public void givenListAllHolidays_whenGetAllHolidays_shouldReturnHolidayDtoList() {
        when(holidayRepository.findAll()).thenReturn(Arrays.asList(holiday));
        List<HolidayDto> holidayDtoList = holidayService.findHoliday();

        assertThat(holidayDtoList).isNotNull();
        assertEquals(holiday.getCityName(), "Santa Rita");
        assertThat(holidayDtoList.get(0).getCityName()).isEqualTo("Santa Rita");
    }

    @Test
    public void givenListHolidaysByDate_whenGetHolidaysByDate_shouldReturnHolidayDtoList() {
        when(holidayRepository.findByDate("2023-05-24")).thenReturn(Arrays.asList(holiday));
        List<HolidayDto> holidayDtoList = holidayService.findHoliday();

        assertThat(holidayDtoList).isNotNull();
        assertEquals(holiday.getDate(), "2023-05-24");
    }

//    @Test
//    public void givenNewHoliday_whenPostHoliday_shouldReturnHolidayDto() throws Exception {
//        when(holidayAdapter.getPublicHoliday("2023", "BR")).thenReturn();
//        HolidayDto holidayDto1 = holidayService.saveHoliday(holidayDto);
//
//        assertEquals(holidayDto1.getCountryCode(), "BR");
//    }


}
