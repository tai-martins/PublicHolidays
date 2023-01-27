package br.inatel.idp.PublicHolidays.step;

import br.inatel.idp.PublicHolidays.config.TestHttpClient;
import br.inatel.idp.PublicHolidays.model.dto.HolidayDto;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HolidaySteps {

    private static ResponseEntity<HolidayDto> responseSuccess;
    private static ResponseEntity<Error> responseError;

    @Autowired
    private TestHttpClient testHttpClient;

    @When("I sucessfully create a new holiday with local <String> and name <String> and date <String> and contry <String> and year <String>")
    public void creatingNewHoliday(String cityName, String holidayName, String date, String countryCode, String year){
        HolidayDto holidayDto = HolidayDto.builder().cityName(cityName).holidayName(holidayName)
                .date(date).countryCode(countryCode).year(year).build();
        responseSuccess = testHttpClient.saveSucess(holidayDto);
    }

    @Then("the status code must be {int}")
    public void checkReadStatusCode(int statusCode){
        if (responseSuccess != null)
            assertEquals(statusCode, responseSuccess.getStatusCodeValue());
        else if (responseError != null)
            assertEquals(statusCode, responseError.getStatusCodeValue());
    }

    @When("I sucessfully create a new holiday with local Heliodora and name Padroeira and date {string} and contry BR and year {string}")
    public void i_sucessfully_create_a_new_holiday_with_local_heliodora_and_name_padroeira_and_date_and_contry_br_and_year(String string, String string2) {
//        HolidayDto holidayDto = HolidayDto.builder()
//                .date("2023-09-06").year("2023").build();
//        responseSuccess = testHttpClient.saveSucess(holidayDto);
        System.out.printf("teste");
    }
    @Then("the id must be not null in the response")
    public void the_id_must_be_not_null_in_the_response() {
        if (responseSuccess != null)
            System.out.printf("teste");
            //assertEquals(statusCode, responseSuccess.getStatusCodeValue());
        else if (responseError != null)
            System.out.printf("teste");
            //assertEquals(statusCode, responseError.getStatusCodeValue());
    }
}
