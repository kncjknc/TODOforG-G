package EmployeesOf.G.G.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "DepartmentTable")
public class Department {

    @Id
    private int departmentId;
    private String departmentName;

    private String departmentManager;

    @OneToMany(mappedBy = "department")
    private List<Employees> employees;

    public Department() {
    }

    public Department(int departmentId, String departmentName, String departmentManager) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentManager = departmentManager;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = departmentManager;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }


    public String getDepartmentManager() {
        return departmentManager;
    }

    public List<Employees> getEmployees() {
        return employees;
    }
}
