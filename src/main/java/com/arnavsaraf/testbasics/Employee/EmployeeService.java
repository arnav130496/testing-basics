package com.arnavsaraf.testbasics.Employee;

import com.arnavsaraf.testbasics.Employee.model.Employee;
import com.arnavsaraf.testbasics.Employee.model.EmployeeUpdateRequest;

import java.util.List;

public interface EmployeeService {

    Employee fetchEmployeeDetailsById(Long id) throws Exception;
    
    Employee fetchEmployeeDetailsByEmail(String email)throws Exception;

    void insertEmployeeData(Employee employeeData)throws Exception;

    void updateEmployeeData(Long id, EmployeeUpdateRequest employeeData) throws Exception;

    void removeEmployeeById(Long id)throws Exception;

    List<Employee> fetchAllEmployees() throws Exception;
}
