package com.romansholokh.openstreetmap.springbootclient.repository;

import com.romansholokh.openstreetmap.springbootclient.entity.Coordinates;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    void whenSaved_thenFindsById() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLat("51.5092768");
        coordinates.setLon("-0.13973813543557267");
        coordinatesRepository.save(coordinates);
        assertThat(coordinatesRepository.findCoordinatesByLat("51.5092768")).isNotNull();
        assertThat(coordinatesRepository.findCoordinatesById(1)).isNotNull();
    }

}