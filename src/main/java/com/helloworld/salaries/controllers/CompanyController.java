package com.helloworld.salaries.controllers;

import com.helloworld.salaries.company.salary.services.AvgSalaryService;
import com.helloworld.salaries.company.salary.services.EmployeeService;
import com.helloworld.salaries.exceptions.NoDataFoundException;
import com.helloworld.salaries.exceptions.WrongParamsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
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
            @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente"),
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
            @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente"),
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
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(salary);
    }

    @PutMapping("/employee/{employeeCode}/salary/{year}/month/{month}")
    public void putEmployeeSalaryInMonth(@PathVariable String employeeCode, @PathVariable int year, @PathVariable int month) {

    }

}
