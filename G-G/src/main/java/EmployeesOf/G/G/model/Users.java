package EmployeesOf.G.G.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name="UsersTable")
@Audited(withModifiedFlag = true)
@AuditTable(value = "USERS_AUDIT")
public class Users {

    @Id
    private int id;
    @Column(unique = true)
    private String userName;

    @NotAudited
    private String password;
    private String roles;

    @JsonIgnore
    @NotAudited
    @OneToOne(mappedBy = "users")
    private Employees employees;

    public Users() {
    }

    public Users(int id, String userName, String password, String roles, Employees employees) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.employees = employees;
    }

    public Users(int id, String userName, String password, String roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                '}';
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
