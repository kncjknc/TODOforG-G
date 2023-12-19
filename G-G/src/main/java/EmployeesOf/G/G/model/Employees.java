package EmployeesOf.G.G.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="EmployeeTable")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequence")
    @SequenceGenerator(name="sequence"
    ,sequenceName = "sequence2"
    ,allocationSize = 1)
    private int employeeId;
    private String employeeName;
    private String employeeAge;
    private String employeeSalary;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "addressId")
    private Address address;


    public Employees() {
    }

    public Employees(int employeeId, String employeeName, String employeeAge, String employeeSalary, Department department, Users users, Address address) {
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
