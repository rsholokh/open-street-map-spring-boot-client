package com.romansholokh.openstreetmap.springbootclient.service;

import com.romansholokh.openstreetmap.springbootclient.entity.Coordinates;
import com.romansholokh.openstreetmap.springbootclient.repository.CoordinatesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CoordinatesService {

    private final CoordinatesRepository coordinatesRepository;

    public Coordinates add(Coordinates coordinates) {
        return coordinatesRepository.save(coordinates);
    }

    public List<Coordinates> getAll() {
        return coordinatesRepository.findAll();
    }
}
