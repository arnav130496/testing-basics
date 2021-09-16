package com.arnavsaraf.testbasics.Employee;

import com.arnavsaraf.testbasics.Employee.model.Employee;
import com.arnavsaraf.testbasics.Employee.model.EmployeeUpdateRequest;
import com.arnavsaraf.testbasics.Employee.utils.NoDataException;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements  EmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    public Employee fetchEmployeeDetailsById(Long id) throws Exception {
        try {
            Employee employee = employeeRepository.getById(id);
            if(employee==null){
                NoDataException noDataException = new NoDataException("Could not find the data");
                throw noDataException;
            }
            return employee;
        }

        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Employee fetchEmployeeDetailsByEmail(String email)throws Exception {
        try {
            Employee employee = employeeRepository.getByEmail(email);
            if (employee == null) {
                throw new NoDataException("Could not find the data");
            }
            return employee;
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void insertEmployeeData(@Valid  Employee employeeData) throws Exception{

        employeeRepository.save(employeeData);
    }

    @Override
    public void updateEmployeeData(Long id, EmployeeUpdateRequest employeeData) throws Exception {

        try {
            Employee employee = employeeRepository.getById(id);
            if(employee==null){
                throw new NoDataException("Could not find the data");
            }
            employee.setDepartment(employeeData.getDepartment());
            employee.setDesignation(employeeData.getDesignation());
            employee.setEmail(employeeData.getEmail());
            employee.setPhoneNumber(employeeData.getPhoneNumber());
            employee.setName(employeeData.getName());

            employeeRepository.save(employee);
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public void removeEmployeeById(Long id)throws Exception {
        try {
            employeeRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            throw new NoDataException("Could not find the data");
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Employee> fetchAllEmployees()throws Exception {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }
}
