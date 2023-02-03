package br.inatel.idp.PublicHolidays.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8088").build();

    @Test
    public void givenAnCorrectGetRequestListAllHolidays_whenCallGetMethod_shouldReturn200Code()throws Exception{
        webTestClient
                .get()
                .uri("/holiday")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk();
    }

    @Test
    public void givenAnCorrectGetRequestHolidayByLocal_whenCallGetMethod_shouldReturn200Code()throws Exception{
        webTestClient
                .get()
                .uri("/holiday/local?cityName=" + "Pouso Alegre")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk();
    }
    @Test
    public void givenAnCorrectGetRequestHolidayByDate_whenCallGetMethod_shouldReturn200Code()throws Exception{
        webTestClient
                .get()
                .uri("/holiday/date?date=" + "2023-05-22")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk();
    }

}
