package br.inatel.idp.PublicHolidays.adapter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import br.inatel.idp.PublicHolidays.model.entity.Holiday;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.idp.PublicHolidays.model.rest.PublicHoliday;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.Cacheable;

@Slf4j
@Service
public class HolidayAdapter {

	@Value("${server.host}")
	private String serverHost;

	@Value("${server.port}")
	private String serverPort;

	@Value("${X-RapidAPI-Host}")
	private String holidayHost;

	private String holidayBaseUrl;

	private WebClient webClient;

	@Value("${X_RapidApi_Key}")
	private String key;

	@PostConstruct
	public void buildHolidayBaseUrl() {
		this.holidayBaseUrl = String.format("https://%s", this.holidayHost);
		this.webClient = WebClient.builder()
				.baseUrl(this.holidayBaseUrl)
				.build();
	}
	
	@Cacheable(value = "holiday")
//	public PublicHoliday listAllPublicHolidays(PublicHoliday publicHoliday){
    public List<PublicHoliday> listAllPublicHolidays(Holiday holiday){

		PublicHoliday pHolidays = new PublicHoliday();
		String contryCode = holiday.getContryCode();
		String year = String.valueOf(holiday.getYear());
				pHolidays = this.webClient
               .get()
               .uri(uriBuilder -> uriBuilder
              //    .path("/2019/US")
					   .path("2023/BR")
					  //.queryParam("year", "{year}")
					  //.queryParam("contryCode", "{contryCode}")
					  .build())
                    .header("X-RapidAPI-Key", key)
                    .header("X-RapidAPI-Host", holidayHost)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(PublicHoliday.class)
                    .block();
            return Arrays.asList(pHolidays);
	}

}
