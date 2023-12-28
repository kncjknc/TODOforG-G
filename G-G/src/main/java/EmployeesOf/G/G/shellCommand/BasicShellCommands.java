package EmployeesOf.G.G.shellCommand;


import EmployeesOf.G.G.controller.CommonController;
import EmployeesOf.G.G.model.Users;

import EmployeesOf.G.G.repository.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class BasicShellCommands {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    private UsersRepository usersRepository;

    Logger logger = LoggerFactory.getLogger(BasicShellCommands.class);


    public BasicShellCommands(RestTemplate restTemplate, ObjectMapper objectMapper){
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
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

    @ShellMethod(key="addBulkUsers", value = "This method will add bulk users")
    public String addUsers(@ShellOption String fileName)  {
        logger.info("Bulk Users");
       try {

           File file = new File(fileName);
           System.out.println("Absolute Path: " + file.getAbsolutePath());

            List<Users> users = readUser(fileName);
            List<String> result = new ArrayList<>();

            for(Users user:users){
                if(!userExist(user)){
                   ResponseEntity<String> addedUsers = restTemplate.exchange(baseUrl+"/addUser",HttpMethod.POST,new HttpEntity<>(user),String.class);
                   result.add(addedUsers.getBody());
                }else{
                    result.add("user Id "+ user.getId()+" already exist so Ignored!");
                }
            }
            return String.join("\n",result);
        }
       catch (IOException j){
           return j.getMessage();
       }
    }

    private boolean userExist(Users user) {
        Users user2 = usersRepository.findById(user.getId()).orElse(null);
        if(user2==null) {
            return false;
        }
        return true;
    }

    private List<Users> readUser(String fileName) throws JsonProcessingException, IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        JsonNode jsonNode = objectMapper.readTree(file);
        return objectMapper.readValue(jsonNode.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, Users.class));
    }


}
