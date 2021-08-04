package com.romansholokh.openstreetmap.springbootclient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romansholokh.openstreetmap.springbootclient.pojo.Address;
import com.romansholokh.openstreetmap.springbootclient.service.CoordinatesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CoordinatesController.class)
class CoordinatesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CoordinatesService coordinatesService;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        Address address = new Address();
        address.setCity("London");
        address.setStreet("Piccadilly");
        address.setStreet("Burlington House");

        mockMvc.perform(post("/getCoordinates")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(address)))
                .andExpect(status().isOk());

    }

    @Test
    public void whenGetJsonFromUrl_thenStatus200() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        String city = "London";
        String street = "Piccadilly";
        String building = "Burlington House";

        String url = "https://nominatim.openstreetmap.org/search/{city}%20{street}%20{building}?format=json&limit=1";

        String givenJson = "[{\"place_id\":98690364,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"way\",\"osm_id\":31510288,\"boundingbox\":[\"51.508883\",\"51.5096454\",\"-0.1403344\",\"-0.1390605\"],\"lat\":\"51.5092768\",\"lon\":\"-0.13973813543557267\",\"display_name\":\"Royal Academy of Arts, Piccadilly, St. James's, City of Westminster, London, Greater London, England, W1J 0BD, United Kingdom\",\"class\":\"tourism\",\"type\":\"museum\",\"importance\":0.7644724074563636,\"icon\":\"https://nominatim.openstreetmap.org/ui/mapicons//tourist_museum.p.20.png\"}]";
        String expectedJson = restTemplate.getForObject(url, String.class, city, street, building);

        System.out.println(expectedJson);

        assertThat(expectedJson, is(givenJson));
    }

    @Test
    public void whenGetAddressJsonFromUrl_thenStatus200() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        String lat = "51.5092768";
        String lon = "-0.13973813543557267";

        String url = "https://nominatim.openstreetmap.org/reverse?format=json&lat={lat}&lon={lon}";

        String givenJson = "{\"place_id\":98690364,\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright\",\"osm_type\":\"way\",\"osm_id\":31510288,\"lat\":\"51.5092768\",\"lon\":\"-0.13973813543557267\",\"display_name\":\"Royal Academy of Arts, Piccadilly, St. James's, City of Westminster, London, Greater London, England, W1J 0BD, United Kingdom\",\"address\":{\"tourism\":\"Royal Academy of Arts\",\"road\":\"Piccadilly\",\"neighbourhood\":\"St. James's\",\"city\":\"City of Westminster\",\"state_district\":\"Greater London\",\"state\":\"England\",\"postcode\":\"W1J 0BD\",\"country\":\"United Kingdom\",\"country_code\":\"gb\"},\"boundingbox\":[\"51.508883\",\"51.5096454\",\"-0.1403344\",\"-0.1390605\"]}";

        String expectedJson = restTemplate.getForObject(url, String.class, lat, lon);

        System.out.println(expectedJson);

        assertThat(expectedJson, is(givenJson));
    }


}