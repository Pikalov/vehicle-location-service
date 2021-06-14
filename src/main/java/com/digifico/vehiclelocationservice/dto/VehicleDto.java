package com.digifico.vehiclelocationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class VehicleDto {

    private String id;

    @Valid
    @NotNull(message = "Location must be specified.")
    private LocationPointDto location;

}
