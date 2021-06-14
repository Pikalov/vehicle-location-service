package com.digifico.vehiclelocationservice.rest;

import com.digifico.vehiclelocationservice.dto.VehicleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
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

    @Operation(summary = "Upsert vehicle location.",
            description = "Creates new vehicle with location or updates the location of existing one.")
    @ApiResponse(responseCode = "200", description = "Ok: The location of vehicle has been saved.")
    @ApiResponse(responseCode = "400",
            description = "Bad request: Validation rules has been violated")
    @PostMapping(
            path = "/vehicle",
            consumes = "application/json"
    )
    ResponseEntity<Void> upsertVehicle(@Valid
                                       @NotNull(message = "Vehicle must be not null to be saved")
                                       @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                               required = true,
                                               description = "Vehicle with its location."
                                       )
                                       @RequestBody VehicleDto vehicleDto);

    @Operation(summary = "Search vehicles inside of rectangle area.",
            description = "Searches for all the vehicles inside of rectangle area specified as longitudes "
                    + "and latitudes of two points (order of points doesn't matter).")
    @ApiResponse(responseCode = "200",
            description = "Ok: The list of vehicles located inside of specified rectangle search area.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VehicleDto.class)))
    @ApiResponse(responseCode = "400",
            description = "Bad request: Validation rules has been violated")
    @GetMapping(
            path = "/vehicles-inside",
            produces = "application/json"
    )
    ResponseEntity<List<VehicleDto>> searchVehiclesInside(
            @Valid
            @NotNull(message = "Longitude must be specified.")
            @DecimalMax(value = "180.0", message = "Longitude value must be between -180 and 180 degrees.")
            @DecimalMin(value = "-180.0", message = "Longitude value must be between -180 and 180 degrees.")
            @Parameter(required = true, description = "Longitude of the first point. Value must be between -180 and 180 degrees")
            @RequestParam("firstLongitude") Double firstPointLongitude,

            @Valid
            @NotNull(message = "Latitude must be specified.")
            @DecimalMax(value = "90.0", message = "Latitude value must be between -90 and 90 degrees.")
            @DecimalMin(value = "-90.0", message = "Latitude value must be between -90 and 90 degrees.")
            @Parameter(required = true, description = "Latitude of the first point. Value must be between -90 and 90 degrees")
            @RequestParam("firstLatitude") Double firstPointLatitude,

            @Valid
            @NotNull(message = "Longitude must be specified.")
            @DecimalMax(value = "180.0", message = "Longitude value must be between -180 and 180 degrees.")
            @DecimalMin(value = "-180.0", message = "Longitude value must be between -180 and 180 degrees.")
            @Parameter(required = true, description = "Longitude of the first point. Value must be between -180 and 180 degrees")
            @RequestParam("secondLongitude") Double secondPointLongitude,

            @Valid
            @NotNull(message = "Latitude must be specified.")
            @DecimalMax(value = "90.0", message = "Latitude value must be between -90 and 90 degrees.")
            @DecimalMin(value = "-90.0", message = "Latitude value must be between -90 and 90 degrees.")
            @Parameter(required = true, description = "Latitude of the second point. Value must be between -90 and 90 degrees")
            @RequestParam("secondLatitude") Double secondPointLatitude);
}
