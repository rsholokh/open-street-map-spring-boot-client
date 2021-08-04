package com.romansholokh.openstreetmap.springbootclient.repository;

import com.romansholokh.openstreetmap.springbootclient.entity.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
    Coordinates findCoordinatesByLat(String lat);

    Coordinates findCoordinatesById(int id);

    List<Coordinates> findAll();
}
