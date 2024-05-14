package com.erainfotechbd.employee.service;


import com.erainfotechbd.employee.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployee ();

    EmployeeDto getEmployeeById (Long id);

    EmployeeDto updateEmployee (EmployeeDto employeeDto, Long id);

    void deleteEmployeeById (Long id);

    EmployeeDto getByEmpId(Long empId);

    EmployeeDto getByEmpMobile (String empMobile);

    EmployeeDto getByEmpName (String empName);

    EmployeeDto updateEmpByPatch (EmployeeDto employeeDto, Long id);

//    void deleteByImpId (Long empId );


}
