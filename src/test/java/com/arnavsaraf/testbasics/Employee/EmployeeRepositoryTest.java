package com.arnavsaraf.testbasics.Employee;

import com.arnavsaraf.testbasics.Employee.model.Employee;
import com.arnavsaraf.testbasics.Employee.utils.NoDataException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    @DisplayName("UT: Find employee by id, exists condition")
    void itShouldFindEmployeeById() {
        //given
        Employee employee = new Employee(3L,"t1@gmail.com",
                "SWE","Software Dev","A Tester","9000000000");

        employeeRepository.save(employee);

        //when
        Employee expectedEmployee = employeeRepository.getById(3L);

        //then
        assertEquals(employee,expectedEmployee,"Employee Exists");
    }

    @Test
    @DisplayName("UT: Find employee by id, not exists condition")
    void itShouldNotFindEmployeeById() {

        //then
        assertNull(employeeRepository.getById(10L));


    }

    @Test
    @DisplayName("UT: Find employee by email,exists condition")
    void itShouldFindEmployeeByEmail() {

        //given
        Employee employee = new Employee(1L,"a@gmail.com",
                "SWE","Software Dev","A Tester","9000000000");

        employeeRepository.save(employee);

        //when
        Employee expectedEmployee = employeeRepository.getByEmail("a@gmail.com");

        //then
        assertEquals(employee,expectedEmployee,"Employee Exists");
    }

    @Test
    @DisplayName("UT: Find employee by email, not exists condition")
    void itShouldNotFindEmployeeByEmail() {

        //then
        assertNull(employeeRepository.getByEmail("a@gmail.com"));
    }

    @Test
    @DisplayName("UT: Check if employee email already exists condition")
    void itShouldCheckIfFindEmployeeByEmailExists(){
        //given
        Employee employee = new Employee(1L,"a@gmail.com",
                "SWE","Software Dev","A Tester","9000000000");

        employeeRepository.save(employee);

        //when
        Employee expectedEmployee = employeeRepository.getByEmail("a@gmail.com");

        //then
        assertTrue(expectedEmployee!=null);
    }

    @Test
    @DisplayName("UT: Check if employee email does not exist condition")
    void itShouldCheckIfFindEmployeeByEmailDoesNotExists(){

        //when
        Employee expectedEmployee = employeeRepository.getByEmail("a1@gmail.com");

        //then
        assertTrue(expectedEmployee==null);
    }

}