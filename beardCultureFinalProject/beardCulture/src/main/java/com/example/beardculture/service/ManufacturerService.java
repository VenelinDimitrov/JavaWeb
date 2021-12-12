package com.example.beardculture.service;

import com.example.beardculture.model.entity.Manufacturer;

public interface ManufacturerService {
    Manufacturer addManufacturer(Manufacturer manufacturer);

    Manufacturer getManufacturerByName(String manufacturerName);
}
