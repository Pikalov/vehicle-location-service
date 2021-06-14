package com.digifico.vehiclelocationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Schema(description = "Vehicle entity with its location.")
public class VehicleDto {

    @Schema(description = "Identifier of vehicle.")
    private String id;

    @Valid
    @NotNull(message = "Location must be specified.")
    @Schema(description = "Point that define vehicle's location.", required = true)
    private LocationPointDto location;

}
