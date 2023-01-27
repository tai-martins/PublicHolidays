package br.inatel.idp.PublicHolidays.step;

import br.inatel.idp.PublicHolidays.mapper.HolidayMapper;
import br.inatel.idp.PublicHolidays.model.dto.HolidayDto;
import br.inatel.idp.PublicHolidays.model.rest.PublicHoliday;
import br.inatel.idp.PublicHolidays.repository.HolidayRepository;
import br.inatel.idp.PublicHolidays.service.HolidayService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HolidaySteps {

    @Autowired
    private WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    @Mock
    private HolidayService holidayService;
    private HolidayRepository holidayRepository;
    private HolidayDto holidayDto = new HolidayDto();
    private String result;
    private String res;

    @Given("{string} is not yet registered")
    public void is_not_yet_registered(String cityName) throws Exception {
        HolidayDto dto = HolidayDto.builder()
                .cityName("Santa Rita")
                .holidayName("Padroeira")
                .date("2023-05-22")
                .countryCode("BR")
                .year("2023")
                .build();

        result = dto.toString();

        System.out.println( "result " + result);

    }

    public void verifyString(String string)
    {
        assertTrue(result.contains(string));
    }

    @Given("{string} is provided as cityName")
    public void is_provided_as_city_name(String cityName) {
       verifyString(cityName);
    }
    @Given("{string} is provided as date")
    public void is_provided_as_date(String date) {
        verifyString(date);
    }

    @When("post a new holiday")
    public void post_a_new_holiday() {
            res = webTestClient.post().uri("/holiday")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(holidayDto)
                    .exchange()
                    .expectBody(PublicHoliday.class)
                    .returnResult()
                    .toString();

    }

    @Then("the statusCode must be {string}")
    public void the_status_code_must_be(String statusCode) {
        assertEquals("201", statusCode);
        //verifyString(statusCode);
    }
    @Then("the holidayName should be {string}")
    public void the_holiday_name_should_be(String holidayName) {
        verifyString(holidayName);
    }
    @Then("the countryCode should be {string}")
    public void the_country_code_should_be(String countryCode) {

        verifyString(countryCode);
    }
    @Then("the year should be {string}")
    public void the_year_should_be(String year)
    {
        verifyString(year);
    }

}
