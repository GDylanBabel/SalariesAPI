package com.helloworld.salaries.company.salary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    @Schema(description = "Identificador")
    @NotNull(message = "No puede ser nulo")
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
    private double salary;
    @Schema(description = "Mes del salario", example = "2")
    private double salaryMonth;
    @Schema(description = "Año del salario", example = "2023")
    private double salaryYear;
}
