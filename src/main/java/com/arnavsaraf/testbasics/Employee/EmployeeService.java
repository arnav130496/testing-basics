package com.arnavsaraf.testbasics.Employee;

import com.arnavsaraf.testbasics.Employee.model.Employee;
import com.arnavsaraf.testbasics.Employee.model.EmployeeUpdateRequest;

import java.util.List;

public interface EmployeeService {

    Employee fetchEmployeeDetailsById(Long id);
    
    Employee fetchEmployeeDetailsByEmail(String email);

    void insertEmployeeData(Employee employeeData);

    void updateEmployeeData(Long id, EmployeeUpdateRequest employeeData) throws Exception;

    void removeEmployeeById(Long id);

    List<Employee> fetchAllEmployees();
}
