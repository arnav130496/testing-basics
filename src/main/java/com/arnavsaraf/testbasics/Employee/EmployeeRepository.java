package com.arnavsaraf.testbasics.Employee;

import com.arnavsaraf.testbasics.Employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query(value = "SELECT * from employee where id= ?1", nativeQuery = true)
    public Employee getById(Long id);
    @Query(value = "SELECT * from employee where email= ?1", nativeQuery = true)
    public Employee getByEmail(String email);
}
