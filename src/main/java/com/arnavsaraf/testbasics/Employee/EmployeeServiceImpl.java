package com.arnavsaraf.testbasics.Employee;

import com.arnavsaraf.testbasics.Employee.model.Employee;
import com.arnavsaraf.testbasics.Employee.model.EmployeeUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements  EmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    public Employee fetchEmployeeDetailsById(Long id) {
        Employee employee = employeeRepository.getById(id);
        return employee;
    }

    @Override
    public Employee fetchEmployeeDetailsByEmail(String email) {
        Employee employee = employeeRepository.getByEmail(email);
        return employee;
    }

    @Override
    public void insertEmployeeData(@Valid  Employee employeeData) {

        employeeRepository.save(employeeData);
    }

    @Override
    public void updateEmployeeData(Long id, EmployeeUpdateRequest employeeData) throws Exception {
        Employee employee = employeeRepository.getById(id);
        if(employee==null){
            throw new Exception("Employee does not exist");
        }
        employee.setDepartment(employeeData.getDepartment());
        employee.setDesignation(employeeData.getDesignation());
        employee.setEmail(employeeData.getEmail());
        employee.setPhoneNumber(employeeData.getPhoneNumber());
        employee.setName(employeeData.getName());

        employeeRepository.save(employee);
    }

    @Override
    public void removeEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> fetchAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }
}
