package com.digifico.vehiclelocationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Location point defined by its longitude and latitude.")
public class LocationPointDto {

    @NotNull(message = "Longitude must be specified.")
    @DecimalMax(value = "180.0", message = "Longitude value must be between -180 and 180 degrees.")
    @DecimalMin(value = "-180.0", message = "Longitude value must be between -180 and 180 degrees.")
    @Schema(description = "Longitude of the point.", required = true)
    private Double longitude;

    @NotNull(message = "Latitude must be specified.")
    @DecimalMax(value = "90.0", message = "Latitude value must be between -90 and 90 degrees.")
    @DecimalMin(value = "-90.0", message = "Latitude value must be between -90 and 90 degrees.")
    @Schema(description = "Latitude of the point.", required = true)
    private Double latitude;

}
