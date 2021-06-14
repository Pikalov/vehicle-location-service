package com.digifico.vehiclelocationservice.service;

import com.digifico.vehiclelocationservice.dto.VehicleDto;
import com.digifico.vehiclelocationservice.mapper.VehicleMapper;
import com.digifico.vehiclelocationservice.persistence.entity.Vehicle;
import com.digifico.vehiclelocationservice.persistence.repository.VehicleRepository;
import com.digifico.vehiclelocationservice.utils.GeometryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Box;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleLocationServiceImpl implements VehicleLocationService {

    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;

    public void upsertVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleMapper.dtoToEntity(vehicleDto);
        vehicle = vehicleRepository.save(vehicle);
        log.info(String.format("Location for vehicle with id=%s has been saved", vehicle.getId()));
    }

    public List<VehicleDto> getAllVehiclesInside(Double firstPointLongitude,
                                                 Double firstPointLatitude,
                                                 Double secondPointLongitude,
                                                 Double secondPointLatitude) {
        Box searchArea = GeometryUtils
                .searchAreaBoxFromPointsCoordinates(firstPointLongitude, firstPointLatitude, secondPointLongitude, secondPointLatitude);
        List<Vehicle> vehiclesInside = vehicleRepository.findAllByLocationWithin(searchArea);
        log.info(String.format("%s vehicles were found inside of provided search area", vehiclesInside.size()));
        return vehiclesInside.stream()
                .map(vehicleMapper::entityToDto)
                .collect(Collectors.toList());
    }

}
