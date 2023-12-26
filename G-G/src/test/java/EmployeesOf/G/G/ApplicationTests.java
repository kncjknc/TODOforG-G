package EmployeesOf.G.G;

import EmployeesOf.G.G.controller.CommonController;
import EmployeesOf.G.G.dto.DepartmentDto;
import EmployeesOf.G.G.dto.DepartmentEmployeeCount;
import EmployeesOf.G.G.dto.EmployeesDto;
import EmployeesOf.G.G.dto.UsersDto;
import EmployeesOf.G.G.entityRepository.DepartmentEntityRepository;
import EmployeesOf.G.G.model.Department;
import EmployeesOf.G.G.model.Users;
import EmployeesOf.G.G.repository.UsersRepository;
import EmployeesOf.G.G.services.CommonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	Logger logger = Logger.getLogger(ApplicationTests.class.getName());

//	@Autowired
//	private CommonController commonController;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Mock
	private UsersRepository usersRepository;

	@Autowired
	private DepartmentEntityRepository departmentEntityRepository;

	ParameterizedTypeReference<List<DepartmentEmployeeCount>> responseType = new ParameterizedTypeReference<List<DepartmentEmployeeCount>>() {};

	ParameterizedTypeReference<List<String>> responseType2 = new ParameterizedTypeReference<List<String>>() {};
	@Test
	void contextLoads() {
		logger.info("Context loaded");
	}

	@Test
	void objectsEquals(){
		ResponseEntity<List<String>> departments =	testRestTemplate.exchange("/getAllDepartmentAces",HttpMethod.GET,null,responseType2);
		List<String> department = departments.getBody();
		List<String> department2 = departmentEntityRepository.getAllDepartmentWithEmployees();
		logger.info("Fetched all the data's from Api and Repository");
		assertEquals(department,department2);
		assertNotNull(department2);
		logger.warning("Checking all the data's or null & contains the same data");
		assertEquals(department,department2);
		logger.info("Completed object equals testing methods");

	}

	@Test
	void getDepartments() {
		logger.info("Welcome to the getDepartments Testing methods");
		ResponseEntity<List<DepartmentEmployeeCount>> depart =	testRestTemplate.exchange("/getEmployeesCount",HttpMethod.GET,null,responseType);
		List<DepartmentEmployeeCount> departments = depart.getBody();
		logger.info("Fetching the all the Departments Details");
		assertNotNull(departments);
	}

	@Test
	void delete(){
		int id = 1;
		logger.info("This is delete api testing method");
		testRestTemplate.delete("/delete/{id}",id);
		logger.info("Successfully deleted id number "+ id);
	}

	@Test
	void addUser(){
		Users user = new Users(7, "varathan4", "varathan4", "Role_Admin");
		logger.info("Created Users");
		ResponseEntity<Users> users = testRestTemplate.postForEntity("/addUser",user,Users.class);
		logger.warning("Push the users object in to the database");
		logger.info("Completed Add user testing method");
	}

	@Test
	void updateDepartment(){
		DepartmentDto department = new DepartmentDto(1,"IT","Kishore");
		testRestTemplate.put("/department/1",department);
		logger.info("Department Updated");
	}



}
