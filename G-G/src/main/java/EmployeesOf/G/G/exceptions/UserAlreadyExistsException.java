package EmployeesOf.G.G.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String userName) {
        super("User name is already exists User Name must be unique " + userName );
    }
}
