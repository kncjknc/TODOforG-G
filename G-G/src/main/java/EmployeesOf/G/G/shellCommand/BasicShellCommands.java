package EmployeesOf.G.G.shellCommand;


import EmployeesOf.G.G.model.Users;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@ShellComponent
public class BasicShellCommands {

    private RestTemplate restTemplate;

    public BasicShellCommands(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    ParameterizedTypeReference<List<String>> response = new ParameterizedTypeReference<List<String>>() {};

    private static final String baseUrl = "http://localhost:9092";

    @ShellMethod(key="getDepartment", value="This method will return Department details")
    public String getDepartmentDetails(@ShellOption int id) throws JsonProcessingException {
       return restTemplate.getForObject(baseUrl+"/getDepartment/"+id,String.class);
    }

    @ShellMethod(key="addUser", value = "This method will add new user")
    public String addUser(@ShellOption int id, String username, String password, String roles){
        Users user = new Users(id,username,password,roles);
       return restTemplate.postForObject(baseUrl+"/addUser",user,String.class);
    }

    @ShellMethod(key = "getDepartments", value = "This method will fetch Department name in ascending order from REST API ")
    public List<String> getUser() {
        ResponseEntity<List<String>> departments = restTemplate.exchange(baseUrl+"/getAllDepartmentAces", HttpMethod.GET,null,response);
        return departments.getBody();
    }

    @ShellMethod(key = "Testing", value = "this method for just testing only")
    public String hello(@ShellOption(defaultValue = "World") String arg){
        return "Hello" + arg;
    }

    @ShellMethod(key="getUser", value = "This method will return User object")
    public String getUser(@ShellOption int id){
       return restTemplate.getForObject(baseUrl+"/user/"+id,String.class);
    }


}
