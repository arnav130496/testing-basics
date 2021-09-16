package com.arnavsaraf.testbasics.Employee;

import com.arnavsaraf.testbasics.Employee.model.Employee;
import com.arnavsaraf.testbasics.Employee.model.EmployeeUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {

    EmployeeService service;

    @GetMapping("/all/employee")
    public List<Employee> fetchEmployeeById(){
        List<Employee> employee = service.fetchAllEmployees();
        return employee;
    }

    @GetMapping("/employee/id/{id}")
    public Employee fetchEmployeeById(@PathVariable("id") Long id){
        Employee employee = service.fetchEmployeeDetailsById(id);
        return employee;
    }

    @GetMapping("/employee/email/{email}")
    public Employee fetchEmployeeDetailsByEmail(@PathVariable("email") String email){
        Employee employee = service.fetchEmployeeDetailsByEmail(email);
        return employee;
    }

    @PostMapping("/employee")
    public void insertEmployeeData(@RequestBody Employee employeeData){
            service.insertEmployeeData(employeeData);
    }


    @PatchMapping("/employee/id/{id}")
    public void updateEmployeeData(@PathVariable("id") Long id, @RequestBody EmployeeUpdateRequest employeeData) throws Exception {
        service.updateEmployeeData(id,employeeData);
    }

    @DeleteMapping("/employee/id/{id}")
    public void removeEmployeeById(@PathVariable("id") Long id){
        service.removeEmployeeById(id);
    }


}
