package com.digifico.vehiclelocationservice.rest;

import com.digifico.vehiclelocationservice.dto.VehicleDto;
import com.digifico.vehiclelocationservice.service.VehicleLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class VehicleLocationControllerImpl implements VehicleLocationController {

    private final VehicleLocationService vehicleLocationService;

    @Override
    public ResponseEntity<Void> upsertVehicle(VehicleDto vehicleDto) {
        log.info(String.format("Request to save location for vehicle with id=%s", vehicleDto.getId()));
        vehicleLocationService.upsertVehicle(vehicleDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<VehicleDto>> searchVehiclesInside(Double firstPointLongitude, Double firstPointLatitude,
                                                                 Double secondPointLongitude, Double secondPointLatitude) {
        log.info(String.format("Request to find all vehicles inside of rectangle spanned between [%s,%s] and [%s,%s] points",
                firstPointLongitude, firstPointLatitude, secondPointLongitude, secondPointLatitude));
        List<VehicleDto> vehiclesInsideSearchArea = vehicleLocationService
                .getAllVehiclesInside(firstPointLongitude, firstPointLatitude, secondPointLongitude, secondPointLatitude);
        return ResponseEntity.ok(vehiclesInsideSearchArea);
    }

}
