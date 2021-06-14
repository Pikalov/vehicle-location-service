package com.digifico.vehiclelocationservice.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
public class LocationPointDto {

    @NotNull(message = "Longitude must be specified.")
    @DecimalMax(value = "180.0", message = "Longitude value must be between -180 and 180 degrees.")
    @DecimalMin(value = "-180.0", message = "Longitude value must be between -180 and 180 degrees.")
    private Double longitude;

    @NotNull(message = "Latitude must be specified.")
    @DecimalMax(value = "90.0", message = "Latitude value must be between -90 and 90 degrees.")
    @DecimalMin(value = "-90.0", message = "Latitude value must be between -90 and 90 degrees.")
    private Double latitude;

}
