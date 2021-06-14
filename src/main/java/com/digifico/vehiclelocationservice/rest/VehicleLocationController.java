package com.digifico.vehiclelocationservice.rest;

import com.digifico.vehiclelocationservice.dto.VehicleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/vehicle-location-service")
public interface VehicleLocationController {

    @PostMapping(
            path = "/vehicle",
            consumes = "application/json"
    )
    ResponseEntity<Void> upsertVehicle(@Valid
                                       @NotNull(message = "Vehicle must be not null to be saved")
                                       @RequestBody VehicleDto vehicleDto);

    @GetMapping(
            path = "/vehicles-inside",
            produces = "application/json"
    )
    ResponseEntity<List<VehicleDto>> searchVehiclesInside(
            @Valid
            @NotNull(message = "Longitude must be specified.")
            @DecimalMax(value = "180.0", message = "Longitude value must be between -180 and 180 degrees.")
            @DecimalMin(value = "-180.0", message = "Longitude value must be between -180 and 180 degrees.")
            @RequestParam("firstLongitude") Double firstPointLongitude,

            @Valid
            @NotNull(message = "Latitude must be specified.")
            @DecimalMax(value = "90.0", message = "Latitude value must be between -90 and 90 degrees.")
            @DecimalMin(value = "-90.0", message = "Latitude value must be between -90 and 90 degrees.")
            @RequestParam("firstLatitude") Double firstPointLatitude,

            @Valid
            @NotNull(message = "Longitude must be specified.")
            @DecimalMax(value = "180.0", message = "Longitude value must be between -180 and 180 degrees.")
            @DecimalMin(value = "-180.0", message = "Longitude value must be between -180 and 180 degrees.")
            @RequestParam("secondLongitude") Double secondPointLongitude,

            @Valid
            @NotNull(message = "Latitude must be specified.")
            @DecimalMax(value = "90.0", message = "Latitude value must be between -90 and 90 degrees.")
            @DecimalMin(value = "-90.0", message = "Latitude value must be between -90 and 90 degrees.")
            @RequestParam("secondLatitude") Double secondPointLatitude);
}
