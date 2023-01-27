package br.inatel.idp.PublicHolidays.adapter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;



import br.inatel.idp.PublicHolidays.exception.ConnectionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.idp.PublicHolidays.model.rest.PublicHoliday;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClientException;


@Slf4j
@Service
public class HolidayAdapter {

	@Value("${X-RapidAPI-Host}")
	private String holidayHost;

	@Value("${X-RapidAPI-URL}")
	private String url;

	@Value("${X_RapidApi_Key}")
	private String key;

	public List<PublicHoliday> getPublicHoliday(String year, String contry){
			try {
				PublicHoliday[] pholidays = WebClient.builder().baseUrl(url)
						.build().get()
						.uri(year+"/"+contry)
						.header("X-RapidAPI-Key", key)
						.header("X-RapidAPI-Host", holidayHost)
						.accept(MediaType.APPLICATION_JSON)
						.retrieve()
						.bodyToMono(PublicHoliday[].class)
						.block();
				return Arrays.asList(pholidays);

			}catch (WebClientException exception){
				throw new ConnectionException(this.url);
			}
	}

}
