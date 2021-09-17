package com.arnavsaraf.testbasics.Employee;

import com.arnavsaraf.testbasics.Employee.model.Employee;
import com.arnavsaraf.testbasics.Employee.model.EmployeeUpdateRequest;
import com.arnavsaraf.testbasics.Employee.utils.NoDataException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;
    private EmployeeServiceImpl service;

    @BeforeEach
    public void init(){
        service = new EmployeeServiceImpl(repository);
    }

    @Test
    @DisplayName("Test to ensure findAll method is run")
    void verifyFindAllIsInvokedForFetchAll() throws Exception {

        //when
        service.fetchAllEmployees();

        //then
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Fetching an employee by id; when employee exists, then success")
    void fetchEmployeeDetailsByIdExists() throws Exception {
        //given
        Employee employee = new Employee(1L,"a@gmail.com",
                "SWE","Software Dev","A Tester","9000000000");

        given(repository.getById(employee.getId()))
                .willReturn(employee);

        //when
        //then
        assertEquals(employee,service.fetchEmployeeDetailsById(1L),"Employee must be equal to the requested employee");
    }

    @Test
    @DisplayName("Fetching an employee by id; when employee does not exists, then success")
    void fetchEmployeeDetailsByIdNotExists() {
        assertThrows(NoDataException.class, () -> service.fetchEmployeeDetailsById(1L), "Method should test if employee id exists");
    }

    @Test
    @DisplayName("Fetching an employee by email; when employee exists, then success")
    void fetchEmployeeDetailsByEmailExists() throws Exception {

        //given
        Employee employee = new Employee(1L,"a@gmail.com",
                "SWE","Software Dev","A Tester","9000000000");

        given(repository.getByEmail(employee.getEmail()))
                .willReturn(employee);

        //when
        //then
        assertEquals(employee,service.fetchEmployeeDetailsByEmail("a@gmail.com"),"Employee must be equal to the requested employee");
    }

    @Test
    @DisplayName("Fetching an employee by email; when employee does not exists, then success")
    void fetchEmployeeDetailsByEmailNotExists() {
        assertThrows(NoDataException.class, () -> service.fetchEmployeeDetailsByEmail("a@gmail.com"), "Method should test if employee id exists");
    }

    @Test
    @DisplayName("Verifying if employee inserted is same as intended")
    void verifyInsertEmployeeIsSame() throws Exception {

        //given
        Employee employee = new Employee(1L,"a@gmail.com",
                "SWE","Software Dev","A Tester","9000000000");

        //when
        service.insertEmployeeData(employee);

        //then
        ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(repository).save(argumentCaptor.capture());

        Employee captured = argumentCaptor.getValue();

        assertEquals(employee,captured,"The employee passed as argument of save must be same as employee object");
    }

    @Test
    @DisplayName("Verifying if employee being inserted has unique email")
    void verifyInsertEmployeeEmailAlreadyExists() {

        //given
        Employee employee = new Employee(1L,"a@gmail.com",
                "SWE","Software Dev","A Tester","9000000000");

        given(repository.existsByEmail(employee.getEmail()))
                .willReturn(true);

        //when
        //then
        RuntimeException myException =  assertThrows(RuntimeException.class, () -> service.insertEmployeeData(employee), "Should fail for email duplicate");
        assertTrue(myException.getMessage().contains("Email already used"),"Exception Message must contain the words 'Email already used' ");
    }

    @Test
    @DisplayName("Verifying if employee inserted is not same as intended")
    void verifyInsertEmployeeIsNotSame() throws Exception {

        //given
        Employee employee = new Employee(1L,"a@gmail.com",
                "SWE","Software Dev","A Tester","9000000000");

        Employee employee2 = new Employee(2L,"b@gmail.com",
                "SWE","Software Dev","B Tester","9000000000");

        //when
        service.insertEmployeeData(employee);

        //then
        ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(repository).save(argumentCaptor.capture());

        Employee captured = argumentCaptor.getValue();

        assertNotEquals(employee2,captured,"The employee passed as argument of save must not be same as employee object");
    }

    @Test
    @DisplayName("Update an employee by Id; when employee exists, then success")
    void updateEmployeeDataWhenIdExists() throws Exception {

        //given
        Employee employee = new Employee(1L,"a@gmail.com",
                "SWE","Software Dev","B Tester","9000000000");
        EmployeeUpdateRequest employeeUpdateRequest = new EmployeeUpdateRequest("a@gmail.com",
                "SWE","Software Dev","B Tester","90000000000");

        given(repository.getById(1L))
                .willReturn(employee);

        //when
        service.updateEmployeeData(1L, employeeUpdateRequest);

        //then
        ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(repository).save(argumentCaptor.capture());

        Employee captured = argumentCaptor.getValue();

        assertEquals(employee,captured,"The employee passed as argument of save must be same as employee object");
    }

    @Test
    @DisplayName("Update an employee by Id; when employee does not exists, then success")
    void updateEmployeeDataWhenIdNotExists() {

        //given
        EmployeeUpdateRequest employeeUpdateRequest = new EmployeeUpdateRequest("a@gmail.com",
                "SWE","Software Dev","B Tester","90000000000");

        given(repository.getById(1L))
                .willReturn(null);

        //when
        //then
        Exception myException = assertThrows(NoDataException.class, ()-> service.updateEmployeeData(1L, employeeUpdateRequest),"Should throw exception when employee not found");
        assertTrue(myException.getMessage().contains("Could not find the data"),"Exception Message must contain the words 'Could not find the data' ");
    }

    @Test
    @DisplayName("Delete an employee by Id; when employee exists, then success")
    void removeEmployeeByIdExists() throws Exception {
        //given
        Employee employee = new Employee(1L,"a@gmail.com",
                "SWE","Software Dev","B Tester","9000000000");

        given(repository.getById(1L))
                .willReturn(employee);

        //when
        service.removeEmployeeById(1L);
        //then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(repository).deleteById(argumentCaptor.capture());
        Long captured = argumentCaptor.getValue();

        assertEquals(employee.getId(),captured,"The ID passed as argument of deleteById must be same as employee ID to be deleted");
    }

    @Test
    @DisplayName("Delete an employee by Id; when employee does not exists, then success")
    void removeEmployeeByIdNotExists(){

        //given
        given(repository.getById(1L))
                .willReturn(null);

        //when
        //then
        Exception myException = assertThrows(NoDataException.class, ()-> service.removeEmployeeById(1L),"Should throw exception when employee not found");
        assertTrue(myException.getMessage().contains("Could not find the data"),"Exception Message must contain the words 'Could not find the data' ");
    }


}