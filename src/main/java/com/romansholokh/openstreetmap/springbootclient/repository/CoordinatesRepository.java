package com.romansholokh.openstreetmap.springbootclient.repository;

import com.romansholokh.openstreetmap.springbootclient.entity.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
}
