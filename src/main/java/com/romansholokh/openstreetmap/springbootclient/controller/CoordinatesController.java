package com.romansholokh.openstreetmap.springbootclient.controller;

import com.romansholokh.openstreetmap.springbootclient.entity.Coordinates;
import com.romansholokh.openstreetmap.springbootclient.pojo.Address;
import com.romansholokh.openstreetmap.springbootclient.service.CoordinatesService;
import com.romansholokh.openstreetmap.springbootclient.util.jsonparser.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class CoordinatesController {

    private final CoordinatesService coordinatesService;

    @PostMapping("/getCoordinates")
    public ResponseEntity getCoordinatesFromAddressAndAddCoordinatesToDB(@RequestBody Address address) {

        String url = "https://nominatim.openstreetmap.org/search/{city}%20{street}%20{building}?format=json&limit=1";

        RestTemplate restTemplate = new RestTemplate();

        String coordinatesJson = null;
        try {
            coordinatesJson = restTemplate.getForObject(url, String.class,
                    address.getCity(),
                    address.getStreet(),
                    address.getBuilding());
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        assert coordinatesJson != null;
        Coordinates coordinates;
        try {
            coordinates = JsonParser.parseJsonToCoordinatesObject(coordinatesJson);
        } catch (Exception e) {
            return new ResponseEntity("Oops, something went wrong. " +
                    "Unable to parse response from the api. " +
                    "Maybe the Address you entered was incorrect.", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(coordinatesService.add(coordinates));

    }

    @GetMapping("/getAddresses")
    public ResponseEntity getAllAddressesFromCoordinatesStoredInDb() {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://nominatim.openstreetmap.org/reverse?format=json&lat={lat}&lon={lon}";

        List<Coordinates> coordinatesList = coordinatesService.getAll();

        List<String> addressList = new ArrayList<>();

        for (Coordinates coordinates : coordinatesList) {
            String addressJson = null;
            try {
                addressJson = restTemplate.getForObject(url, String.class, coordinates.getLat(), coordinates.getLon());
            } catch (RestClientException e) {
                e.printStackTrace();
            }
            assert addressJson != null;
            Address address;
            try {
                address = JsonParser.parseJsonToAddressObject(addressJson);
            } catch (Exception e) {
                return new ResponseEntity("Oops, something went wrong. " +
                        "Unable to parse response from the api. " +
                        "Maybe the Coordinates was incorrect.", HttpStatus.NOT_ACCEPTABLE);
            }
            String fullAddress = address.getFullAddress();
            if (fullAddress != null) {
                addressList.add(address.getFullAddress());
            } else {
                addressList.add("Error. Unable to get full address from the coordinates: " + coordinates.toString());
            }
        }

        return ResponseEntity.ok(addressList);
    }
}
