package com.helloworld.salaries.company.salary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class Office {
    @Schema(description = "Identificador")
    @NotNull(message = "No puede ser nulo")
    @PositiveOrZero
    private int id;
    @Schema(description = "Nombre de la oficina", example = "Central Oviedo")
    @NotNull(message = "El nombre no puede ser nulo")
    private String name;
    @Schema(description = "La ciudad de la oficina", example = "Oviedo")
    @NotNull(message = "El ciudad no puede ser nulo")
    private String city;
    @Schema(description = "El codigo psotal de la oficina", example = "33011")
    @NotNull(message = "El codigo psotal no puede ser nulo")
    private String postalCode;
    @Schema(description = "El codigo psotal de la oficina", example = "ES")
    @NotNull(message = "El codigo psotal no puede ser nulo")
    private String countryCode;
    @Schema(description = "El codigo del headquarter de la oficina", example = "1")
    private int headquarter;
    @Schema(description = "El continente de la oficina", example = "Europa")
    @NotNull(message = "El continente no puede ser nulo")
    private String continent;
}
