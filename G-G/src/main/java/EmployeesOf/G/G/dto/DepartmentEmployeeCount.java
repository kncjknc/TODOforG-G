package EmployeesOf.G.G.dto;

public class DepartmentEmployeeCount {

    private String managerName;
    private String deparmentName;
    private Long count;

    public DepartmentEmployeeCount() {
    }


    public DepartmentEmployeeCount(String managerName, String deparmentName, Long count) {
        this.managerName = managerName;
        this.deparmentName = deparmentName;
        this.count = count;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setDeparmentName(String deparmentName) {
        this.deparmentName = deparmentName;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getDeparmentName() {
        return deparmentName;
    }

    public Long getCount() {
        return count;
    }
}
