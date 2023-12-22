package EmployeesOf.G.G.dto;


import EmployeesOf.G.G.model.Address;
import EmployeesOf.G.G.model.Department;
import EmployeesOf.G.G.model.Users;

public class EmployeesDto {

    private int employeeId;
    private String employeeName;
    private String employeeAge;
    private String employeeSalary;
    private Department department;
    private Users users;
    private Address address;

    public EmployeesDto() {
    }

    public EmployeesDto(int employeeId, String employeeName, String employeeAge, String employeeSalary, Department department, Users users, Address address) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeSalary = employeeSalary;
        this.department = department;
        this.users = users;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeAge(String employeeAge) {
        this.employeeAge = employeeAge;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public void setUsers(Users users) {
        this.users = users;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeAge() {
        return employeeAge;
    }

    public String getEmployeeSalary() {
        return employeeSalary;
    }

    public Department getDepartment() {
        return department;
    }
    public Users getUsers() {
        return users;
    }
}
