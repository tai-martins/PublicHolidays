package br.inatel.idp.PublicHolidays.service;

import br.inatel.idp.PublicHolidays.adapter.HolidayAdapter;
import br.inatel.idp.PublicHolidays.model.dto.HolidayDto;
import br.inatel.idp.PublicHolidays.model.entity.Holiday;
import br.inatel.idp.PublicHolidays.repository.HolidayRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
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

    @BeforeEach
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
    }

    @Test
    public void givenListHolidaysByLocal_whenGetHolidaysByValidLocal_shouldReturnHolidayDtoList() {
        when(holidayRepository.findByCityName(any(String.class))).thenReturn(Arrays.asList(holiday));

        List<HolidayDto> holidayDtoList = holidayService.findHolidayByLocal("Santa Rita");

        assertThat(holidayDtoList).isNotNull();
        assertThat(holidayDtoList.get(0).getCityName()).isEqualTo("Santa Rita");
    }

}
