package com.example.beardculture.service.impl;

import com.example.beardculture.model.entity.Manufacturer;
import com.example.beardculture.repository.ManufacturerRepository;
import com.example.beardculture.service.ManufacturerService;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Manufacturer addManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer getManufacturerByName(String manufacturerName) {
        return manufacturerRepository.getByName(manufacturerName).orElse(null);
    }
}
