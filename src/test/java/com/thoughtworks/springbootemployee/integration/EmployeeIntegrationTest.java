package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;
    private List<Employee> dummyEmployees;

    @BeforeEach
    public void setupData() {
        dummyEmployees = Arrays.asList((new Employee("Debids", 19, "male", 1000000, 1)),
                (new Employee("Joanna", 21, "female", 1000000, 1)),
                (new Employee("Dibidi", 18, "female", 1234, 1)),
                (new Employee("Barnakol", 21, "male", 1000000, 2)),
                (new Employee("Bobby", 18, "male", 1000000, 2)));
        employeeRepository.saveAll(dummyEmployees);
    }

    @AfterEach
    public void deleteDataAfter() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_employees_when_getAllEmployees() throws Exception {
        //when and then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Debids"))
                .andExpect(jsonPath("$[0].age").value(19))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(1000000));
    }

    @Test
    void should_return_employee_when_findById_given_employee_id() throws Exception {
        int id = dummyEmployees.get(0).getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Debids"))
                .andExpect(jsonPath("$.age").value(19))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(1000000))
                .andExpect(jsonPath("$.company_id").value(1));
    }

    @Test
    void should_return_employees_when_findByGender_given_employee_gender() throws Exception {
        String gender = "male";
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", gender)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].gender", Matchers.hasItems(gender)));
    }

    @Test
    void should_return_three_employee_per_list_when_getListByPagination_given_pageIndex_and_pageSize() throws Exception {
        int pageSize = 2;
        int pageIndex = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .param("pageindex", String.valueOf(pageIndex)).param("pagesize", String.valueOf(pageSize))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(pageSize)));
    }

    @Test
    void should_add_employee_when_addEmployee_given_employee_information() throws Exception {
        //given
        String employee = "{\n" +
                "    \"id\": 42,\n" +
                "    \"name\": \"DIVIDII\",\n" +
                "    \"age\": 42,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 20,\n" +
                "    \"company_id\": 1\n" +
                "}";

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("DIVIDII"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value("20"))
                .andExpect(jsonPath("$.company_id").value("1"));

    }

    @Test
    void should_update_when_updateEmployee_given_updated_employee_information() throws Exception {
        //given
        Employee employee = new Employee("DIVAD", 18, "male", 420);
        String newEmployeeInfo = "{\n" +
                "    \"name\": \"VIDAVID\",\n" +
                "    \"age\": 21,\n" +
                "    \"gender\": \"female\",\n" +
                "    \"salary\": 960,\n" +
                "    \"company_id\": 1\n" +
                "}";

        //when and then
        Employee updatedEmployee = employeeRepository.save(employee);
        int id = updatedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployeeInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("VIDAVID"))
                .andExpect(jsonPath("$.age").value("21"))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value("960"))
                .andExpect(jsonPath("$.company_id").value("1"));
    }

    @Test
    void should_delete_employee_when_deleteEmployee_given_the_employee_id() throws Exception {
        //given
        Employee employeeToDelete = new Employee(469, "DeleteLater", 65, "male", 20, 1);
        Employee addedEmployee = employeeRepository.save(employeeToDelete);

        //when
        int id = addedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
