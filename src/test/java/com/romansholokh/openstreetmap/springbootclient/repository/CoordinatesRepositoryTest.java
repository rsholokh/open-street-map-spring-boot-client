package com.romansholokh.openstreetmap.springbootclient.repository;

import com.romansholokh.openstreetmap.springbootclient.entity.Coordinates;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class CoordinatesRepositoryTest  {

    @Autowired
    private CoordinatesRepository coordinatesRepository;

    @Test
    void injectedComponentIsNotNull(){
        assertThat(coordinatesRepository).isNotNull();
    }

    @Test
    void whenSaved_thenFindsById_and_findByLat() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLat("51.5092768");
        coordinates.setLon("-0.13973813543557267");
        coordinatesRepository.save(coordinates);
        assertThat(coordinatesRepository.findCoordinatesByLat("51.5092768")).isNotNull();
        assertThat(coordinatesRepository.findCoordinatesById(1)).isNotNull();
    }

    @Test
    void whenSaved_thenGetAll() {
        List<Coordinates> coordinatesList = new ArrayList<>();

        Coordinates coordinates = new Coordinates();

        coordinates.setLat("51.5092768");
        coordinates.setLon("-0.13973813543557267");
        coordinatesList.add(coordinates);
        coordinatesRepository.save(coordinates);

        Coordinates coordinates2 = new Coordinates();

        coordinates2.setLat("51.49627545");
        coordinates2.setLon("31.315322574330125");
        coordinatesList.add(coordinates2);
        coordinatesRepository.save(coordinates2);

        assertThat(coordinatesRepository.findAll()).isEqualTo(coordinatesList);

    }

}