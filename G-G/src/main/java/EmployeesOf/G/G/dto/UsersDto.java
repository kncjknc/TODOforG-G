package EmployeesOf.G.G.dto;


import EmployeesOf.G.G.model.Employees;


public class UsersDto {

    private int id;
    private String userName;
    private String password;
    private String roles;

    private Employees employees;

    public UsersDto() {
    }

    public UsersDto(int id, String userName, String password, String roles, Employees employees) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.employees = employees;
    }

    public UsersDto(int id, String userName, String password, String roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public Employees getEmployees() {
        return employees;
    }
}
