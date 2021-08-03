package com.romansholokh.openstreetmap.springbootclient.controller;

import com.romansholokh.openstreetmap.springbootclient.entity.Coordinates;
import com.romansholokh.openstreetmap.springbootclient.pojo.Address;
import com.romansholokh.openstreetmap.springbootclient.service.CoordinatesService;
import com.romansholokh.openstreetmap.springbootclient.util.jsonparser.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class CoordinatesController {

    private final CoordinatesService coordinatesService;

    @PostMapping("/getCoordinates")
    public ResponseEntity<Coordinates> getCoordinatesFromAddressAndAddCoordinatesToDB(@RequestBody Address address) {
        String city = address.getCity();
        String street = address.getStreet();
        String building = address.getBuilding();

        String url = "https://nominatim.openstreetmap.org/search/{city}%20{street}%20{building}?format=json&limit=1";

        RestTemplate restTemplate = new RestTemplate();

        String coordinatesJson = null;
        try {
            coordinatesJson = restTemplate.getForObject(url, String.class, city, street, building);
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        assert coordinatesJson != null;
        Coordinates coordinates = null;
        try {
            coordinates = JsonParser.parseJsonToCoordinatesObject(coordinatesJson);
        } catch (Exception e) {
            return new ResponseEntity("Oops, something went wrong. " +
                    "Unable to parse response from the api. " +
                    "Maybe the Address you entered was incorrect.", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(coordinatesService.add(coordinates));
    }
}
