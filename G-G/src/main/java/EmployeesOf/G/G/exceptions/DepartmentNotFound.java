package EmployeesOf.G.G.exceptions;

public class DepartmentNotFound extends RuntimeException{

    public DepartmentNotFound(String department){
        super("Department "+ department +" Not Found");
    }

}
