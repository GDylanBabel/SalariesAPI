package com.helloworld.salaries.company.salary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    @Schema(description = "Identificador")
    @NotNull(message = "No puede ser nulo")
    @PositiveOrZero
    private int id;
    @Schema(description = "Codigo de empleado", example = "000102")
    @NotNull(message = "El codigo del empleado no puede ser nulo")
    private String codeEmpleado;
    @Schema(description = "Coste hora", example = "6.44")
    private double costeHora;
    @Schema(description = "Nombre empleado", example = "Jose Mota")
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombreEmpleado;
    @Schema(description = "Salario", example = "944.16")
    @PositiveOrZero
    private double salary;
    @Schema(description = "Mes del salario", example = "2")
    @Min(value = 1, message = "El mes no debe ser menos de 1")
    @Max(value = 12, message = "El mes no debe ser mayor que 12")
    private double salaryMonth;
    @Schema(description = "Año del salario", example = "2023")
    @Min(value = 1920, message = "El año no debe ser menos que 1920")
    @Max(value = 2024, message = "El año no debe ser mayor que 2024")
    private double salaryYear;
}
