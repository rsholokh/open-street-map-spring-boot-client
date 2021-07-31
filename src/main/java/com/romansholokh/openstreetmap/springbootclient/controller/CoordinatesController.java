package com.romansholokh.openstreetmap.springbootclient.controller;

import com.romansholokh.openstreetmap.springbootclient.pojo.Address;
import com.romansholokh.openstreetmap.springbootclient.service.CoordinatesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class CoordinatesController {

    private final CoordinatesService coordinatesService;

    @GetMapping("/getCoordinates")
    public ResponseEntity getCoordinatesFromAddress (@RequestBody Address address) {
        return null;
    }
}
