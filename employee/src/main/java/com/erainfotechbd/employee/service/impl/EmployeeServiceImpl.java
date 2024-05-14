package com.erainfotechbd.employee.service.impl;

import com.erainfotechbd.employee.dto.EmployeeDto;
import com.erainfotechbd.employee.entity.Employee;
import com.erainfotechbd.employee.exception.ResourceNotFoundException;
import com.erainfotechbd.employee.repository.EmployeeRepository;
import com.erainfotechbd.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper mapper;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper=mapper;
    }


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        //convert DTO to entity
        Employee employee=mapToEntity(employeeDto);
        Employee newEmployee= employeeRepository.save(employee);

        //convert entity into DTO
        EmployeeDto employeeResponse=mapToDTO(newEmployee);

        return employeeResponse;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees= employeeRepository.findAll();
        return employees.stream().map(employee -> mapToDTO(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee", "id",id));
        return mapToDTO(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        //get post by id from the database
       Employee employee= employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee", "id",id));

        employee.setEmpId(employeeDto.getEmpId());
        employee.setEmpName(employeeDto.getEmpName());
        employee.setEmpMobile(employeeDto.getEmpMobile());
        employee.setEmpSalary(employeeDto.getEmpSalary());

        Employee updateEmployee = employeeRepository.save(employee);

        return mapToDTO(updateEmployee);

    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee", "id",id));
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDto getByEmpId(Long empId) {

        Employee employee=employeeRepository.findByEmpId(empId);
        EmployeeDto employeeDto=mapToDTO(employee);
        return employeeDto;
    }

    @Override
    public EmployeeDto getByEmpMobile(String empMobile) {

        Employee employee=employeeRepository.findByEmpMobile(empMobile);
        return mapToDTO(employee);
    }

    @Override
    public EmployeeDto getByEmpName(String empName) {
        Employee employee=employeeRepository.findByEmpName(empName);
        return mapToDTO(employee);
    }

    @Override
    public EmployeeDto updateEmpByPatch(EmployeeDto employeeDto, Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("employee", "id",id));

//        employee.setEmpId(employeeDto.getEmpId());
//        employee.setEmpName(employeeDto.getEmpName());
//        employee.setEmpMobile(employeeDto.getEmpMobile());
//        employee.setEmpSalary(employeeDto.getEmpSalary());

        employee=mapToEntity(employeeDto);
        employee.setId(id);

        Employee updateEmployee = employeeRepository.save(employee);

        return mapToDTO(updateEmployee);
    }

//    @Override
//    public void deleteByImpId(Long empId) {
//        Employee employee=employeeRepository.deletelByEmpId(empId);
//        employeeRepository.delete(employee);
//    }


    // entity into dto
    private EmployeeDto mapToDTO(Employee employee){
        EmployeeDto employeeDto=mapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    //convert dto into entity
    private Employee mapToEntity(EmployeeDto employeeDto){
        Employee employee=mapper.map(employeeDto, Employee.class);
        return employee;

    }
}
