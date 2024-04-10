package com.helloworld.salaries.company.salary.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDTO {
    private String nombreempleado;
    private String codEmpleado;
    private Date fechainicio;
    private Date fechafin;
    private int office;
}
