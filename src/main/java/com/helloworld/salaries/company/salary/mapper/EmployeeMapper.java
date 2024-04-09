package com.helloworld.salaries.company.salary.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT SUM(s.salary) FROM Salary s where s.codempleado = #{codeEmpleado} and s.salaryyear=#{year}")
    Optional<Double> salaryOnYear(String codeEmpleado, int year);
}
