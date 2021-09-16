package com.arnavsaraf.testbasics.Employee;

import com.arnavsaraf.testbasics.Employee.model.Employee;
import com.arnavsaraf.testbasics.Employee.model.EmployeeUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class EmployeeController {

    EmployeeService service;

    @GetMapping("/all/employee")
    public List<Employee> fetchEmployeeById() throws Exception {
        List<Employee> employee = service.fetchAllEmployees();
        return employee;
    }

    @GetMapping("/employee/id/{id}")
    public Employee fetchEmployeeById(@PathVariable("id") Long id)throws Exception {
        Employee employee = service.fetchEmployeeDetailsById(id);
        return employee;
    }

    @GetMapping("/employee/email/{email}")
    public Employee fetchEmployeeDetailsByEmail(@PathVariable("email") String email)throws Exception {
        Employee employee = service.fetchEmployeeDetailsByEmail(email);
        return employee;
    }

    @PostMapping("/employee")
    public void insertEmployeeData(@RequestBody Employee employeeData)throws Exception {
            service.insertEmployeeData(employeeData);
    }


    @PutMapping("/employee/id/{id}")
    public void updateEmployeeData(@PathVariable("id") Long id, @RequestBody EmployeeUpdateRequest employeeData) throws Exception {
        service.updateEmployeeData(id,employeeData);
    }

    @DeleteMapping("/employee/id/{id}")
    public void removeEmployeeById(@PathVariable("id") Long id)throws Exception {
        service.removeEmployeeById(id);
    }


}
