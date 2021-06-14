package com.digifico.vehiclelocationservice.service;

import com.digifico.vehiclelocationservice.dto.VehicleDto;

import java.util.List;

public interface VehicleLocationService {

    void upsertVehicle(VehicleDto vehicleDto);

    List<VehicleDto> getAllVehiclesInside(Double firstPointLongitude,
                                          Double firstPointLatitude,
                                          Double secondPointLongitude,
                                          Double secondPointLatitude);

}
