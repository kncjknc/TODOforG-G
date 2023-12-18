package EmployeesOf.G.G.model;

import jakarta.persistence.*;

@Entity
@Table(name = "AddressTable")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressId;
    private String address1;
    private String address2;
    private String postalCode;

    @OneToOne(mappedBy = "address")
    private Employees employees;

    public Address(int addressId, String address1, String address2, String postalCode, Employees employees) {
        this.addressId = addressId;
        this.address1 = address1;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.employees = employees;
    }

    public Address() {
    }

    public int getAddressId() {
        return addressId;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
}
