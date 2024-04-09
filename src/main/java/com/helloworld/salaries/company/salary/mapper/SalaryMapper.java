package com.helloworld.salaries.company.salary.mapper;

import com.helloworld.salaries.company.salary.model.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SalaryMapper {
    @Select("SELECT * FROM Salary")
    List<Salary> findAll();

    @Select("SELECT AVG(s.salary) FROM Salary s WHERE salaryYear = #{year}")
    Optional<Double> avgSalary(int year);
}
