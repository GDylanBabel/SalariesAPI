package com.helloworld.salaries.company.salary.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;

public class Employee {
    @Schema(description = "Identificador")
    @NotNull(message = "No puede ser nulo")
    @PositiveOrZero
    private int id;
    @Schema(description = "Codigo de empleado", example = "000102")
    @NotNull(message = "El codigo del empleado no puede ser nulo")
    private String codeEmployee;
    @Schema(description = "Nombre empleado", example = "Jose Mota")
    @NotNull(message = "El nombre no puede ser nulo")
    private String nameEmpployee;
    @Schema(description = "La fecha de inicio de trabajo del empleado", example = "12/12/2022")
    @PastOrPresent
    private Date fechaInicio;
    @Schema(description = "La fecha de fin de trabajo del empleado", example = "12/12/2022")
    @FutureOrPresent
    private Date fechaBaja;
    @Schema(description = "La oficina en la que se encuentra situado el empelado", example = "1")
    @PositiveOrZero
    private int office;
}
