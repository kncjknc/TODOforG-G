package EmployeesOf.G.G.dto;

import EmployeesOf.G.G.model.Employees;

import java.util.List;

public class DepartmentDto {

    private int departmentId;
    private String departmentName;
    private String departmentManager;

    private List<Employees> employees;

    public DepartmentDto() {
    }

    public DepartmentDto(int departmentId, String departmentName, String departmentManager, List<Employees> employees) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentManager = departmentManager;
        this.employees = employees;
    }

    public DepartmentDto(int departmentId, String departmentName, String departmentManager) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentManager = departmentManager;
    }

    public DepartmentDto(String departmentName, List<Employees> employees) {
        this.departmentName = departmentName;
        this.employees = employees;
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
