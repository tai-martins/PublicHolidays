package br.inatel.idp.PublicHolidays.config;

import br.inatel.idp.PublicHolidays.model.dto.HolidayDto;
import br.inatel.idp.PublicHolidays.model.rest.Error;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class TestHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String HOLIDAY_ENDPOINT = "holiday";
    private final RestTemplate restTemplate = new RestTemplate();
    @LocalServerPort
    private int port;

    private String holidayEndpoint(){
        return SERVER_URL + ":" + port + HOLIDAY_ENDPOINT;
    }

    public ResponseEntity<HolidayDto> saveSucess(HolidayDto holidayDto){
        String cityName = holidayDto.getCityName();
        String holidayName= holidayDto.getHolidayName();
        String date = holidayDto.getDate();
        String countryCode = holidayDto.getCountryCode();
        String year = holidayDto.getYear();

        return restTemplate.postForEntity(holidayEndpoint(), holidayDto, HolidayDto.class);
    }

    public ResponseEntity<Error> saveError(HolidayDto holidayDto){
        try{
            restTemplate.postForEntity(holidayEndpoint(), holidayDto, Error.class);
        }catch (HttpClientErrorException httpClientErrorException){
            Error error = new Gson().fromJson(httpClientErrorException.getResponseBodyAsString(), Error.class);
            return new ResponseEntity<>(error, httpClientErrorException.getStatusCode());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<HolidayDto> getSucess(String cityName){
        return restTemplate.getForEntity(String.format("%s/local?cityName=", holidayEndpoint(), cityName), HolidayDto.class);
    }

    public ResponseEntity<Error> getError(String cityName){
        try{
            restTemplate.getForEntity(String.format("%s/local?cityName=", holidayEndpoint(), cityName), Error.class);
        }catch (HttpClientErrorException httpClientErrorException){
            Error error = new Gson().fromJson(httpClientErrorException.getResponseBodyAsString(), Error.class);
            return new ResponseEntity<>(error, httpClientErrorException.getStatusCode());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
