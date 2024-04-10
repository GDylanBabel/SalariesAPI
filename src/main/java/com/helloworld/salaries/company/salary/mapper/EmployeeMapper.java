package com.helloworld.salaries.company.salary.mapper;

import com.helloworld.salaries.company.salary.model.Employee;
import com.helloworld.salaries.company.salary.model.dto.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM Employee e WHERE e.nombreempleado like #{name} AND e.codempleado like #{code} OFFSET #{start} ROWS FETCH NEXT #{end} ROWS only;")
    public List<EmployeeDTO> getEmployee(String name, String code, int start, int end);
}