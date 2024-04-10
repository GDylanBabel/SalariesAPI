package com.helloworld.salaries.company.salary.mapper;

import com.helloworld.salaries.company.salary.model.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SalaryMapper {
    @Select("SELECT * FROM Salary")
    List<Salary> findAll();

    @Select("SELECT AVG(s.salary) FROM Salary s WHERE salaryYear = #{year}")
    Optional<Double> avgSalary(int year);

    @Select("SELECT SUM(s.salary) FROM Salary s where s.codempleado = #{employeeCode} AND s.salaryyear=#{year}")
    Optional<Double> salaryOnYear(String employeeCode, int year);

    @Update("UPDATE Salary s SET s.salary=#{salary} WHERE s.codempleado='#{employeeCode}' AND s.salarymonth=#{month} AND s.salaryyear=#{year}")
    void putEmployeeSalaryInMonth(String employeeCode, int month, int year, double salary);
}
