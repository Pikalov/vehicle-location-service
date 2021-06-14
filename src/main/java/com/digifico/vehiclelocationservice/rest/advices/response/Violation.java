package com.digifico.vehiclelocationservice.rest.advices.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Validation rule violation.")
public class Violation {

    @Schema(description = "Name of the field which violated validation rule.")
    private final String fieldName;

    @Schema(description = "Message that describes the violated rule.")
    private final String message;

}
