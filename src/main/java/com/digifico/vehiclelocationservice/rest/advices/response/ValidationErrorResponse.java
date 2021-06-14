package com.digifico.vehiclelocationservice.rest.advices.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Error response for failed validation.")
public class ValidationErrorResponse {

    @Schema(description = "List of rule violations.")
    private List<Violation> violations = new ArrayList<>();

}
