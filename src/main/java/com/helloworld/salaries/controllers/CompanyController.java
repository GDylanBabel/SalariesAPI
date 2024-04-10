package com.helloworld.salaries.controllers;

import com.helloworld.salaries.company.salary.model.Employee;
import com.helloworld.salaries.company.salary.model.dto.EmployeeDTO;
import com.helloworld.salaries.company.salary.model.dto.SalaryDTO;
import com.helloworld.salaries.company.salary.services.AvgSalaryService;
import com.helloworld.salaries.company.salary.services.EmployeeService;
import com.helloworld.salaries.exceptions.NoDataFoundException;
import com.helloworld.salaries.exceptions.WrongParamsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Digits;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@Tag(
        name = "Gestor de Compañia",
        description = "Este controlador se encarga de gestionar las peticiones sobre la información de la compañia"
)
public class CompanyController {

    private final AvgSalaryService avgSalaryService;
    private final EmployeeService employeeService;

    public CompanyController(AvgSalaryService avgSalaryService, EmployeeService employeeService) {
        this.avgSalaryService = avgSalaryService;
        this.employeeService = employeeService;
    }

    @GetMapping("/salary/{year}/avg")
    @Operation(summary = "Salario medio anual de la empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente", content = @Content(
                    mediaType = "text/plain",
                    schema = @Schema(
                            implementation = Double.class
                    )
            )),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos para ese año"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<?> getAvgSalary(@PathVariable int year) {
        Double avgSalary = null;
        try {
            avgSalary = this.avgSalaryService.getAvgSalary(year);
            return ResponseEntity.ok(avgSalary);
        } catch (WrongParamsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NoDataFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/employee/{employeeCode}/salary/{year}")
    @Operation(summary = "Salario de un empleado en un año", description = "El salario total para un empleado durante un año concreto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente", content = @Content(
                    mediaType = "text/plain",
                    schema = @Schema(
                            implementation = Double.class
                    ))),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos para ese año o ese empleados"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<?> getEmployeeSalaryInYear(@PathVariable @Parameter(name = "employeeCode", description = "Codigo empleado", example = "000102")
                                                     String employeeCode,
                                                     @PathVariable @Parameter(name = "year", description = "Año para el salario del empleado", example = "2023")
                                                     int year) {
        double salary = 0;
        try {
            salary = employeeService.getSalaryInYear(employeeCode, year);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(salary);
    }

    @PutMapping("/employee/{employeeCode}/salary/{year}/month/{month}")
    @Operation(summary = "Actualizar salario mes", description = "Actualiza el salario en un mes de un año de un empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos actualizados correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos para ese empleados"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public void putEmployeeSalaryInMonth(@PathVariable @Parameter(name = "employeeCode", description = "Codigo empleado", example = "000102")
                                         String employeeCode,
                                         @PathVariable @Parameter(name = "year", description = "Año para el salario del empleado", example = "2023")
                                         int year,
                                         @PathVariable @Parameter(name = "month", description = "Mes para el salario del empleado", example = "12")
                                         int month,
                                         @RequestBody @Parameter(name = "salaryDTO", description = "DTO con un campo salary que actualizar", example = "{\"salary\":123.2")
                                         SalaryDTO salaryDTO) {
        employeeService.putEmployeeSalaryInMonth(employeeCode, year, month, salaryDTO.getSalary());
    }

    @GetMapping("/employee")
    @Operation(summary = "Obtener empleado", description = "Recupera los datos de un empleado dado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos recuperados correctamente", content = @Content(
                    mediaType = "text/plain",
                    schema = @Schema(
                            implementation = Double.class
                    ))),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos para ese empleados"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<?> getEmployee(@RequestParam(required = false) @Parameter(name = "name", description = "El nombre del empleado", example = "Juan")
                                         String name,
                                         @RequestParam(required = false) @Parameter(name = "employeeCode", description = "Codigo empleado", example = "000102")
                                         String codEmployee,
                                         @RequestParam(required = true) @Parameter(name = "page", description = "La página que se va a buscar", example = "1")
                                         int page) {
        List<EmployeeDTO> employeeList = employeeService.getEmployee(name, codEmployee, page);
        return ResponseEntity.ok(employeeList);
    }

    @PostMapping("/employee/{employeeCode}/salary/{year}")
    @Operation(summary = "Insertar salario anual", description = "Inserta todos los salarios mensuales de un empleado para un año")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos introducidos correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos para ese empleados"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public void setEmployeeSalaryAnnual(@PathVariable @Parameter(name = "employeeCode", description = "Codigo empleado", example = "000102")
                                        String employeeCode,
                                        @PathVariable @Parameter(name = "year", description = "Año para el salario del empleado", example = "2023")
                                        int year) {

    }
}
