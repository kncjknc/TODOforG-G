package EmployeesOf.G.G.dto;


import lombok.Data;

@Data
public class AuthTokenclass {

    private String userName;
    private String passWord;

    public AuthTokenclass() {

    }

    public String getUserName() {
        return userName;
    }
    public String getPassWord() {
        return passWord;
    }

    public AuthTokenclass(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }



}